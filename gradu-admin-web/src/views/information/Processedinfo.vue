<template>
  <!--已处理信息-->
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户名" />
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户Id" />
      <el-input v-model="listQuery.mobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入手机号" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >导出</el-button>
    </div>
    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="序号" width="70" fixed="left">
        <template slot-scope="scopes">
          {{ scopes.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" width="90" label="姓名" prop="graduUser.nickname" fixed="left" />
      <el-table-column align="center" width="150" label="学号" prop="username" fixed="left" />
      <el-table-column align="center" width="210" label="开始时间" prop="createTime" />
      <el-table-column align="center" width="210" label="结束时间" prop="endTime" />
      <el-table-column align="center" width="150" label="单次时长（分钟）" prop="duration" />
      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag>{{ genderDic[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="210" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />
    <!-- 信息编辑对话框 -->
    <el-dialog :visible.sync="infoDialogVisible" title="信息处理" class="style" :append-to-body="true">
      <div class="dialogDiv">
        <el-form ref="listQuery" :model="listQuery" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
          <el-form-item label="学号" prop="username">
            <el-input v-model="Detail.username" :disabled="true" />
          </el-form-item>
          <el-form-item label="开始时间" prop="createTime">
            <el-date-picker v-model="Detail.createTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" />
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker v-model="Detail.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" />
          </el-form-item>
          <el-form-item label="当前状态" prop="status">
            <el-input v-model="Detail.status" />
          </el-form-item>
          <el-form-item label="义工描述" prop="status">
            <el-input type="textarea" :rows="4" v-model="Detail.desc" />
          </el-form-item>
          <el-form-item label="活动图片" prop="img2">
            <el-upload
              :action="uploadPath"
              :limit="5"
              :file-list="imgFileList"
              :headers="headers"
              :on-exceed="uploadOverrun"
              :on-success="handlePictureUrl"
              :on-remove="handleRemove"
              :on-preview="handlePreview"
              multiple
              accept=".jpg,.jpeg,.png,.gif"
              list-type="picture-card"
            >
              <i class="el-icon-plus" />
            </el-upload>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="infoDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpdate">更新</el-button>
      </div>
    </el-dialog>
    <!-- 返回顶部 -->
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  </div>
</template>

<script>
import { listVoInfo, updateInfo } from '@/api/volunInfo'
import Pagination from '@/components/Pagination'
import BackToTop from '@/components/BackToTop'
import { getToken } from '@/utils/auth'
import { uploadPath } from '@/api/storage'

export default {
  name: 'Processedinfo',
  components: { BackToTop, Pagination },
  data() {
    return {
      uploadPath,
      list: null,
      total: 0,
      listLoading: true,
      imgFileList: [],
      listQuery: {
        page: 1,
        limit: 20,
        createTime: undefined,
        endTime: undefined,
        mobile: undefined,
        userId: undefined
      },
      genderDic: ['未审核', '已审核'],
      downloadLoading: false,
      infoDialogVisible: false,
      Detail: {}
    }
  },
  computed: {
    headers() {
      return {
        'X-Gradu-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listVoInfo(1).then(response => {
        if (response.data.data) {
          this.list = response.data.data.list
          this.total = response.data.data.total
          console.log(this.list)
          this.listLoading = false
        } else {
          this.list = []
          this.total = 0
          this.listLoading = false
        }
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '手机号码', '性别', '生日', '状态']
        const filterVal = ['username', 'mobile', 'gender', 'birthday', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    handleDetail(row) {
      this.Detail = row
      this.imgFileList = []
      for (var i = 0; i < this.Detail.img2.length; i++) {
        this.imgFileList.push({
          url: this.Detail.img2[i]
        })
      }
      this.infoDialogVisible = true
    },
    handleUpdate() {
      updateInfo(this.Detail)
        .then((response) => {
          this.infoDialogVisible = false
          this.$notify.success({
            title: '成功',
            message: '修改成功'
          })
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
      this.getList()
    },

    uploadPicUrl: function(response) {
      this.Detail.img2 = response.data.url
    },
    uploadOverrun: function() {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传5张图片!'
      })
    },
    handlePictureUrl(response, file, fileList) {
      if (response.errno === 0) {
        this.Detail.img2.push(response.data.url)
      }
    },
    handleRemove: function(file, fileList) {
      for (var i = 0; i < this.Detail.img2.length; i++) {
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
        if (this.Detail.img2[i] === url) {
          this.Detail.img2.splice(i, 1)
        }
      }
    },

    handlePreview(file) {
      console.log(file)
      window.open(file.url)
    }
  }
}
</script>

<style>
.dialogDiv {
  height: 400px;
  overflow: auto;
}

.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>
