package club.luckylight.service.impl;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.mapper.FlowMapper;
import club.luckylight.mapper.PermissionMapper;
import club.luckylight.model.Permission;
import club.luckylight.model.flow.*;
import club.luckylight.service.PermissionService;
import club.luckylight.util.ODLUtils;
import club.luckylight.vo.flow.AddFlowRequestVo;
import club.luckylight.vo.permission.PermissionAddRequestVo;
import club.luckylight.vo.permission.PermissionListRequestVo;
import club.luckylight.vo.permission.PermissionUpdateRequestVo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private FlowMapper flowMapper;

    @Override
    public Page<Permission> getPermissionList(PermissionListRequestVo vo) {
        // 分页
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());

        return permissionMapper.getPermissionList();
    }

    @Override
    public List<UseablePermissionDto> getUseablePermission() {
        return permissionMapper.getUseablePermission();
    }

    @Override
    public Boolean addPermission(PermissionAddRequestVo vo) {
        int flowId = getFlowId();
        String result;
        String flowBody;

        EthernetType ethernetType = new EthernetType();
        ethernetType.setType("2048");

        EthernetMatch ethernetMatch = new EthernetMatch();
        ethernetMatch.setEthernetType(ethernetType);

        Match match = new Match();
        match.setEthernetMatch(ethernetMatch);
        match.setIpv4Destination(vo.getPermissionIp() + "/32");

        if (ObjectUtil.isNotNull(vo.getPermissionPort())) {
            IpMatch ipMatch = new IpMatch();
            ipMatch.setIpProtocol("6");
            match.setIpMatch(ipMatch);
            match.setTcpDestinationPort(vo.getPermissionPort().toString());
        }

        ApplyActions applyActions = new ApplyActions();
        applyActions.setAction(new DropAction());

        Instruction instruction = new Instruction();
        instruction.setApplyActions(applyActions);

        Instructions instructions = new Instructions();
        instructions.setInstruction(instruction);

        Flow flow = new Flow();
        flow.setFlowId(String.valueOf(flowId));
        flow.setIdleTimeout(0L);
        flow.setHardTimeout(0L);
        flow.setPriority("100");
        flow.setTableId("0");
        flow.setMatch(match);
        flow.setInstructions(instructions);

        List<Flow> flowList = new ArrayList<>();
        flowList.add(flow);

        AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
        addFlowRequestVo.setFlow(flowList);

        flowBody = JSON.toJSONString(addFlowRequestVo);
        result = ODLUtils.addFlow("60.205.190.37", "8181", "1", flowId, flowBody);

        // 如果下发失败
        if (StrUtil.isNotBlank(result)) {
            return false;
        }

        Permission permission = new Permission();
        permission.setPermissionName(vo.getPermissionName());
        permission.setPermissionIp(vo.getPermissionIp());
        permission.setPermissionPort(vo.getPermissionPort());
        permission.setCreateTime(new Date());
        permission.setUpdateTime(new Date());
        permission.setStatus((byte) 1);
        permission.setRemark(vo.getRemark());
        permission.setFlowId(flowId);
        permission.setFlowBody(flowBody);

        return permissionMapper.insert(permission) == 1;
    }

    @Override
    public Boolean banPermission(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(permission)) {
            return false;
        }

        String result = ODLUtils.deleteFlow("60.205.190.37", "8181", "1", permission.getFlowId());
        if (StrUtil.isNotBlank(result)) {
            return false;
        }

        return permissionMapper.banPermission(id) == 1;
    }

    @Override
    public Boolean allowPermission(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(permission)) {
            return false;
        }

        String result = ODLUtils.addFlow("60.205.190.37", "8181", "1", permission.getFlowId(), permission.getFlowBody());
        if (StrUtil.isNotBlank(result)) {
            return false;
        }

        return permissionMapper.allowPermission(id) == 1;
    }

    @Override
    public void banSelectionPermission(List<Integer> ids) {
        ids.forEach(this::banPermission);
    }

    @Override
    public void allowSelectionPermission(List<Integer> ids) {
        ids.forEach(this::allowPermission);
    }

    @Override
    public Boolean updatePermission(PermissionUpdateRequestVo vo) {
        String addResult;
        String flowBody;

        String deleteResult = ODLUtils.deleteFlow("60.205.190.37", "8181", "1", vo.getFlowId());
        if (StrUtil.isNotBlank(deleteResult)) {
            return false;
        }

        EthernetType ethernetType = new EthernetType();
        ethernetType.setType("2048");

        EthernetMatch ethernetMatch = new EthernetMatch();
        ethernetMatch.setEthernetType(ethernetType);

        Match match = new Match();
        match.setEthernetMatch(ethernetMatch);
        match.setIpv4Destination(vo.getPermissionIp() + "/32");

        if (ObjectUtil.isNotNull(vo.getPermissionPort())) {
            match.setTcpDestinationPort(vo.getPermissionPort().toString());

            IpMatch ipMatch = new IpMatch();
            ipMatch.setIpProtocol("6");
            match.setIpMatch(ipMatch);
        }

        ApplyActions applyActions = new ApplyActions();
        applyActions.setAction(new DropAction());

        Instruction instruction = new Instruction();
        instruction.setApplyActions(applyActions);

        Instructions instructions = new Instructions();
        instructions.setInstruction(instruction);

        Flow flow = new Flow();
        flow.setFlowId(vo.getFlowId().toString());
        flow.setIdleTimeout(0L);
        flow.setHardTimeout(0L);
        flow.setPriority("100");
        flow.setTableId("0");
        flow.setMatch(match);
        flow.setInstructions(instructions);

        List<Flow> flowList = new ArrayList<>();
        flowList.add(flow);

        AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
        addFlowRequestVo.setFlow(flowList);

        flowBody = JSON.toJSONString(addFlowRequestVo);
        addResult = ODLUtils.addFlow("60.205.190.37", "8181", "1", vo.getFlowId(), flowBody);

        // 如果下发失败
        if (StrUtil.isNotBlank(addResult)) {
            return false;
        }

        Permission permission = new Permission();
        permission.setPermissionId(vo.getPermissionId());
        permission.setPermissionName(vo.getPermissionName());
        permission.setPermissionIp(vo.getPermissionIp());
        permission.setPermissionPort(vo.getPermissionPort());
        permission.setUpdateTime(new Date());
        permission.setRemark(vo.getRemark());
        permission.setFlowBody(flowBody);

        return permissionMapper.updatePermission(permission) == 1;
    }

    private int getFlowId() {
        club.luckylight.model.Flow flow = new club.luckylight.model.Flow();
        flow.setCreateTime(new Date());
        flow.setUpdateTime(new Date());
        flowMapper.insert(flow);

        return flow.getId();
    }
}
