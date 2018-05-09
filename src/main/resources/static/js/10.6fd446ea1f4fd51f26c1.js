webpackJsonp([10],{BepB:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("vMJZ"),l={data:function(){return{list:null,listLoading:!0,listQuery:{currentPage:1,pageSize:10},total:0,multipleSelection:[]}},created:function(){this.fetchData()},methods:{fetchData:function(){var e=this;this.listLoading=!0,Object(a.f)(this.listQuery).then(function(t){e.list=t.data.userList,e.total=t.data.total,e.listLoading=!1})},handleSizeChange:function(){},handleCurrentChange:function(){},statusFilter:function(e,t){return t.status===e},handleSelectionChange:function(e){this.multipleSelection=e},addUser:function(){this.$router.push({name:"UserAdd"})},banSelectionUser:function(){var e=this;null!=this.multipleSelection&&0!==this.multipleSelection.length?this.$confirm("确定禁用选中用户?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){for(var t=[],n=0;n<e.multipleSelection.length;n++)1===e.multipleSelection[n].status&&t.push(e.multipleSelection[n].id);Object(a.d)(t).then(function(t){e.$message({type:"success",message:"禁用成功!"}),e.fetchData()})}).catch(function(){e.$message({type:"info",message:"取消禁用"})}):this.$message({type:"info",message:"请至少选择一项"})},allowSelectionUser:function(){var e=this;null!=this.multipleSelection&&0!==this.multipleSelection.length?this.$confirm("确定启用选中用户?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){for(var t=[],n=0;n<e.multipleSelection.length;n++)0===e.multipleSelection[n].status&&t.push(e.multipleSelection[n].id);Object(a.b)(t).then(function(t){e.$message({type:"success",message:"启用成功!"}),e.fetchData()})}).catch(function(){e.$message({type:"info",message:"取消启用"})}):this.$message({type:"info",message:"请至少选择一项"})},banUser:function(e){var t=this;0!==e.status&&Object(a.e)(e.id).then(function(e){t.$message({message:"禁用成功！",type:"success"}),t.fetchData()})},allowUser:function(e){var t=this;1!==e.status&&Object(a.c)(e.id).then(function(e){t.$message({message:"启用成功！",type:"success"}),t.fetchData()})},editUser:function(e){this.$router.push({name:"UserEdit",params:e})}}},i={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("div",{staticClass:"tool-bar"},[n("el-button-group",[n("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:e.addUser}},[e._v("新增用户")]),e._v(" "),n("el-button",{attrs:{type:"danger",icon:"el-icon-delete"},on:{click:e.banSelectionUser}},[e._v("禁用选中用户")]),e._v(" "),n("el-button",{attrs:{type:"success",icon:"el-icon-check"},on:{click:e.allowSelectionUser}},[e._v("启用选中用户")])],1)],1),e._v(" "),n("div",[n("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:e.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:e.list,"element-loading-text":"Loading",border:""},on:{"selection-change":e.handleSelectionChange}},[n("el-table-column",{attrs:{type:"selection",align:"center","header-align":"center",width:"50"}}),e._v(" "),n("el-table-column",{attrs:{align:"center","header-align":"center",label:"Id",prop:"id"}}),e._v(" "),n("el-table-column",{attrs:{label:"用户名",align:"center","header-align":"center",prop:"username"}}),e._v(" "),n("el-table-column",{attrs:{label:"头像",align:"center","header-align":"center",width:"60"},scopedSlots:e._u([{key:"default",fn:function(e){return[n("img",{staticClass:"user-avatar",attrs:{src:e.row.avatarUrl}})]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"角色",align:"center","header-align":"center",prop:"roleName"}}),e._v(" "),n("el-table-column",{attrs:{label:"电话",align:"center","header-align":"center",prop:"mobile"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(null==t.row.mobile?"无":t.row.mobile))]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"邮箱",align:"center","header-align":"center",prop:"email"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(null==t.row.email?"无":t.row.email))]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"状态",align:"center","header-align":"center",prop:"status",filters:[{text:"启用",value:1},{text:"禁用",value:0}],"filter-method":e.statusFilter,"filter-placement":"bottom",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-tag",{attrs:{type:1===t.row.status?"success":"danger"}},[e._v(e._s(1===t.row.status?"启用":"禁用"))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",prop:"createTime","header-align":"center",label:"创建时间",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("i",{staticClass:"el-icon-time"}),e._v(" "),n("span",[e._v(e._s(t.row.createTime))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center","header-align":"center",prop:"updateTime",label:"更新时间",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("i",{staticClass:"el-icon-time"}),e._v(" "),n("span",[e._v(e._s(t.row.updateTime))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center","header-align":"center",label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",plain:""},on:{click:function(n){e.editUser(t.row)}}},[e._v("编辑")]),e._v(" "),1===t.row.status?n("el-button",{attrs:{slot:"reference",size:"mini",type:"danger"},on:{click:function(n){e.banUser(t.row)}},slot:"reference"},[e._v("禁用")]):n("el-button",{attrs:{slot:"reference",size:"mini",type:"success"},on:{click:function(n){e.allowUser(t.row)}},slot:"reference"},[e._v("启用")])]}}])})],1)],1),e._v(" "),n("div",{staticStyle:{"margin-top":"20px",float:"right"}},[n("el-pagination",{attrs:{"current-page":e.listQuery.currentPage,"page-sizes":[10,20,30,40,50,100,200],"page-size":e.listQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total,"prev-text":"上一页","next-text":"下一页"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)])},staticRenderFns:[]};var s=n("VU/8")(l,i,!1,function(e){n("mkuJ")},null,null);t.default=s.exports},mISQ:function(e,t,n){(e.exports=n("FZ+f")(!1)).push([e.i,"\n.app-container .user-avatar {\n  width: 40px;\n  height: 40px;\n  border-radius: 10px;\n  vertical-align: middle;\n}\n.app-container .tool-bar {\n  margin-bottom: 20px;\n}\n",""])},mkuJ:function(e,t,n){var a=n("mISQ");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);n("rjj0")("37dff4c1",a,!0)}});