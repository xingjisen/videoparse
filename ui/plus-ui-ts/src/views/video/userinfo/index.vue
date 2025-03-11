<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="位置" prop="getlocation">
              <el-input v-model="queryParams.getlocation" placeholder="请输入位置" clearable @keyup.enter="handleQuery"/>
            </el-form-item>
            <el-form-item label="ip地址" prop="ip">
              <el-input v-model="queryParams.ip" placeholder="请输入ip地址" clearable @keyup.enter="handleQuery"/>
            </el-form-item>
            <el-form-item label="用户代理" prop="useragent">
              <el-input v-model="queryParams.useragent" placeholder="请输入用户代理" clearable @keyup.enter="handleQuery"/>
            </el-form-item>
            <el-form-item label="请求时间" prop="reqTime">
              <el-date-picker clearable
                              v-model="queryParams.reqTime"
                              type="date"
                              value-format="YYYY-MM-DD"
                              placeholder="请选择请求时间"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>

    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <!--          <el-col :span="1.5">
                      <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['video:info:add']">新增</el-button>
                    </el-col>
                    <el-col :span="1.5">
                      <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['video:info:edit']">修改</el-button>
                    </el-col>
                    <el-col :span="1.5">
                      <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['video:info:remove']">删除</el-button>
                    </el-col>-->
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['video:info:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
        <!--        <el-table-column label="主键" align="center" show-overflow-tooltip prop="id" v-if="true"/>-->
        <el-table-column label="请求头" align="center" show-overflow-tooltip prop="headers" width="180"/>
        <el-table-column label="平台" align="center" show-overflow-tooltip prop="headers" width="180">
          <template #default="scope">
            <span>{{ JSON.parse(scope.row.headers).headers['sec-ch-ua-platform'].replaceAll('"', '') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="位置" align="center" show-overflow-tooltip prop="getlocation" width="180">
          <template #default="scope">
            <span>{{ JSON.parse(scope.row.getlocation).reason || (JSON.parse(scope.row.getlocation).region + JSON.parse(scope.row.getlocation).city) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="协议版本" align="center" show-overflow-tooltip prop="getlocation" width="180">
          <template #default="scope">
            <span>{{ JSON.parse(scope.row.getlocation).version }}</span>
          </template>
        </el-table-column>
        <el-table-column label="国家" align="center" show-overflow-tooltip prop="getlocation" width="180">
          <template #default="scope">
            <span>{{ JSON.parse(scope.row.getlocation)['country_name'] }}</span>
          </template>
        </el-table-column>
        <el-table-column label="ip地址" align="center" show-overflow-tooltip prop="ip" width="180"/>
        <el-table-column label="用户代理" align="center" show-overflow-tooltip prop="useragent" width="180"/>
        <el-table-column label="访问时间" align="center" show-overflow-tooltip prop="reqTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.reqTime, '{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <!--        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                  <template #default="scope">
                    <el-tooltip content="修改" placement="top">
                      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['video:info:edit']"></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['video:info:remove']"></el-button>
                    </el-tooltip>
                  </template>
                </el-table-column>-->
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList"/>
    </el-card>
    <!-- 添加或修改访问信息对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="infoFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="位置" prop="getlocation">
          <el-input v-model="form.getlocation" placeholder="请输入位置"/>
        </el-form-item>
        <el-form-item label="ip地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入ip地址"/>
        </el-form-item>
        <el-form-item label="用户代理" prop="useragent">
          <el-input v-model="form.useragent" placeholder="请输入用户代理"/>
        </el-form-item>
        <el-form-item label="请求时间" prop="reqTime">
          <el-date-picker clearable
                          v-model="form.reqTime"
                          type="datetime"
                          value-format="YYYY-MM-DD"
                          placeholder="请选择请求时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Info" lang="ts">

import {InfoForm, InfoQuery, InfoVO} from "@/api/video/userinfo/types";
import {listInfo} from "@/api/video/userinfo";
import {parseTime} from "../../../utils/ruoyi";

const {proxy} = getCurrentInstance() as ComponentInternalInstance;

const infoList = ref<InfoVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const infoFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: InfoForm = {
  id: undefined,
  headers: undefined,
  getlocation: undefined,
  ip: undefined,
  useragent: undefined,
  reqTime: undefined,
}
const data = reactive<PageData<InfoForm, InfoQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    headers: undefined,
    getlocation: undefined,
    ip: undefined,
    useragent: undefined,
    reqTime: undefined,
    params: {}
  },
  rules: {
    id: [
      {required: true, message: "主键不能为空", trigger: "blur"}
    ],
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询访问信息列表 */
const getList = async () => {
  loading.value = true;
  const res = await listInfo(queryParams.value);
  infoList.value = res.rows;
  total.value = res.total;
  loading.value = false;
}

/** 取消按钮 */
const cancel = () => {
  reset();
  dialog.visible = false;
}

/** 表单重置 */
const reset = () => {
  form.value = {...initFormData};
  infoFormRef.value?.resetFields();
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields();
  handleQuery();
}

/** 多选框选中数据 */
const handleSelectionChange = (selection: InfoVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加访问信息";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: InfoVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getInfo(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改访问信息";
}

/** 提交按钮 */
const submitForm = () => {
  infoFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateInfo(form.value).finally(() => buttonLoading.value = false);
      } else {
        await addInfo(form.value).finally(() => buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: InfoVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除访问信息编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delInfo(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('video/info/export', {
    ...queryParams.value
  }, `info_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
