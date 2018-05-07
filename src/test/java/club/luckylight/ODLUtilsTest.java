package club.luckylight;

import club.luckylight.model.flow.*;
import club.luckylight.util.ODLUtils;
import club.luckylight.vo.flow.AddFlowRequestVo;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 ODLUtils
 */
public class ODLUtilsTest {

    /**
     * 下发三层流表
     */
    @Test
    public void testAddThreeLayerFlow() {
        EthernetType ethernetType = new EthernetType();
        ethernetType.setType("2048");

        EthernetMatch ethernetMatch = new EthernetMatch();
        ethernetMatch.setEthernetType(ethernetType);

        Match match = new Match();
        match.setEthernetMatch(ethernetMatch);
        match.setIpv4Destination("10.0.0.5/32");

        ApplyActions applyActions = new ApplyActions();
        applyActions.setAction(new DropAction());

        Instruction instruction = new Instruction();
        instruction.setApplyActions(applyActions);

        Instructions instructions = new Instructions();
        instructions.setInstruction(instruction);

        Flow flow = new Flow();
        flow.setFlowId("13");
        flow.setIdleTimeout(0L);
        flow.setHardTimeout(0L);
        flow.setPriority("300");
        flow.setTableId("0");
        flow.setMatch(match);
        flow.setInstructions(instructions);

        List<Flow> flowList = new ArrayList<>();
        flowList.add(flow);

        AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
        addFlowRequestVo.setFlow(flowList);

        System.out.println(JSON.toJSONString(addFlowRequestVo));

        String result = ODLUtils.addFlow("60.205.190.37", "8181", "1", 13, JSON.toJSONString(addFlowRequestVo));
        System.out.println("==========");
        System.out.println(StrUtil.isBlank(result));
    }

    /**
     * 下发四层流表
     */
    @Test
    public void testAddFourLayerFlow() {
        EthernetType ethernetType = new EthernetType();
        ethernetType.setType("2048");

        EthernetMatch ethernetMatch = new EthernetMatch();
        ethernetMatch.setEthernetType(ethernetType);

        IpMatch ipMatch = new IpMatch();
        ipMatch.setIpProtocol("6");

        Match match = new Match();
        match.setEthernetMatch(ethernetMatch);
        match.setIpMatch(ipMatch);
        match.setIpv4Source("10.0.0.1/32");
        match.setIpv4Destination("10.0.0.5/32");
        match.setTcpDestinationPort("8080");

        ApplyActions applyActions = new ApplyActions();
        applyActions.setAction(new NormalAction());

        Instruction instruction = new Instruction();
        instruction.setApplyActions(applyActions);

        Instructions instructions = new Instructions();
        instructions.setInstruction(instruction);

        Flow flow = new Flow();
        flow.setFlowId("12");
        flow.setIdleTimeout(0L);
        flow.setHardTimeout(0L);
        flow.setPriority("200");
        flow.setTableId("0");
        flow.setMatch(match);
        flow.setInstructions(instructions);

        List<Flow> flowList = new ArrayList<>();
        flowList.add(flow);

        AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
        addFlowRequestVo.setFlow(flowList);

        System.out.println(JSON.toJSONString(addFlowRequestVo));

        String result = ODLUtils.addFlow("60.205.190.37", "8181", "1", 12, JSON.toJSONString(addFlowRequestVo));
        System.out.println("==========");
        System.out.println(StrUtil.isBlank(result));
    }

    /**
     * 删除流表
     */
    @Test
    public void testDeleteFlow() {
        String result = ODLUtils.deleteFlow("60.205.190.37", "8181", "1", 2);
        System.out.println(result);
    }
}
