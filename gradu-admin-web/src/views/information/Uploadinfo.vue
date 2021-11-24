<template>
  <div class="formstyle">
    <!--  上传信息  -->
    <el-form ref="dataForm" :model="dataForm" :rules="rules" size="medium" label-width="250px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="dataForm.name" placeholder="请输入姓名" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="学号" prop="username">
            <el-input v-model="dataForm.username" type="username" placeholder="请输入学号" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="开始选择" prop="createTime">
            <el-date-picker v-model="dataForm.createTime" :picker-options="expireTimeOption" style="width: 360px" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="结束选择" prop="endTime">
            <el-date-picker v-model="dataForm.endTime" :picker-options="expireEndTimeOption" style="width: 360px" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="义工信息描述" prop="desc">
            <el-input
              v-model="dataForm.desc"
              type="textarea"
              placeholder="请输入义工信息描述"
              :autosize="{minRows: 4, maxRows: 8}"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="上传-多张">
          <el-upload
            v-model="dataForm.img2"
            :action="uploadPath"
            :limit="5"
            :headers="headers"
            :on-exceed="uploadOverrun"
            :on-success="handleInfoyUrl"
            :on-remove="handleRemove"
            multiple
            accept=".jpg,.jpeg,.png,.gif"
            list-type="picture-card"
          >
            <i class="el-icon-plus" />
          </el-upload>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item size="large">
          <el-button type="primary" @click="createData">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { createInfo } from '@/api/volunInfo'
import { getToken } from '@/utils/auth'
import { uploadPath } from '@/api/storage'

export default {
  name: 'Uploadinfo',
  components: {},
  props: [],
  data() {
    return {
      uploadPath,
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      dataForm: {
        username: undefined,
        createTime: undefined,
        endTime: undefined,
        desc: undefined,
        status: 1,
        upload: '',
        img2: []
      },
      rules: {
        name: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        username: [{
          required: true,
          message: '请输入学号',
          trigger: 'blur'
        }],
        createTime: [{
          required: true,
          message: '请选择日期选择',
          trigger: 'change'
        }],
        endTime: [{
          required: true,
          message: '时间范围不能为空',
          trigger: 'change'
        }],
        desc: [{
          required: true,
          message: '请输入义工信息描述',
          trigger: 'blur'
        }]
      },
      /*
      * 时间限制
      * */
      expireTimeOption: {
        disabledDate(date) {
          return date.getTime() > Date.now()
        }
      },
      expireEndTimeOption: {
        disabledDate: time => {
          if (this.dataForm.createTime) {
            return time.getTime() > Date.now() || time.getTime() < new Date(this.dataForm.createTime).getTime()
          } else {
            return time.getTime() > Date.now()
          }
        }
      }
    }
  },
  computed: {
    headers() {
      return {
        'X-Gradu-Admin-Token': getToken()
      }
    }
  },
  watch: {},
  created() {},
  mounted() {},
  methods: {
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createInfo(this.dataForm)
            .then(response => {
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '信息上传成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
        this.resetForm()
      })
    },
    resetForm() {
      this.$refs['dataForm'].resetFields()
    },
    handleDownload(file) {
      console.log(file)
    },
    uploadOverrun: function() {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传5张图片!'
      })
    },
    handleInfoyUrl(response, file, fileList) {
      if (response.errno === 0) {
        this.dataForm.img2.push(response.data.url)
        // console.log(this.dataForm.img2.push(response.data.url))
      }
    },
    handleRemove: function(file, fileList) {
      for (var i = 0; i < this.dataForm.img2.length; i++) {
        // 这里存在两种情况
        // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
        //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
        // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
        var url
        if (file.response === undefined) {
          url = file.url
        } else {
          url = file.response.data.url
        }
        if (this.dataForm.img2[i] === url) {
          this.dataForm.img2.splice(i, 1)
        }
      }
    }
  }
}

</script>
<style >
.el-upload__tip {
  line-height: 1.2;
}
.formstyle {
  margin: 10px;
  padding: 20px;
  border: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}

</style>
