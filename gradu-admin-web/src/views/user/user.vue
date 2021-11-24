<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入学号" />
      <el-input v-model="listQuery.nickname" clearable class="filter-item" style="width: 200px;" placeholder="请输入姓名" />
      <el-input v-model="listQuery.mobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入手机号" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <input ref="excel-upload-input" class="excel-upload-input" type="file" accept=".xlsx, .xls" @change="handleClick">
      <el-button
        :loading="uploadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-upload2"
        style="margin-left:10px"
        @click="handleUpload"
      >导入
      </el-button>
      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        style="margin-left:10px"
        @click="handleDownload"
      >导出
      </el-button>

    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="序号" width="70" fixed="left">
        <template slot-scope="scopes">
          {{ scopes.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="姓名" prop="nickname" width="100px" fixed />

      <el-table-column align="center" width="110" label="学号" prop="username" fixed />

      <el-table-column align="center" label="性别" prop="gender" width="70px">
        <template slot-scope="scope">
          <el-tag>{{ genderDic[scope.row.gender] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" width="135" label="所属二级学院" prop="secondCollege" sortable />

      <el-table-column align="center" width="110" label="所属班级" prop="classBanji" />

      <el-table-column align="center" width="120" label="所属专业" prop="major" />

      <el-table-column align="center" width="110" label="专业方向" prop="majorDirection" />

      <el-table-column align="center" width="110" label="手机号码" prop="mobile" />

      <el-table-column align="center" width="110" label="生日" prop="birthday" />

      <el-table-column align="center" label="操作" width="350" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDetail(scope.row)">基本信息详情</el-button>
          <el-button type="primary" size="mini" @click="handleInfoDetail(scope.row)">义工信息详情</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
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
    <!-- 用户编辑对话框 -->
    <el-dialog :visible.sync="userDialogVisible" title="用户编辑" class="style" :append-to-body="true">
      <div class="dialogDiv">
        <el-form
          ref="userDetail"
          :model="userDetail"
          status-icon
          label-position="left"
          label-width="100px"
          style="width: 400px; margin-left:50px;"
        >
          <el-form-item label="学号" prop="username">
            <el-input v-model="userDetail.username" :disabled="true" />
          </el-form-item>
          <el-form-item label="姓名" prop="nickname">
            <el-input v-model="userDetail.nickname" />
          </el-form-item>
          <el-form-item label="用户性别" prop="gender">
            <el-select v-model="userDetail.gender" placeholder="请选择">
              <el-option v-for="(item, index) in genderDic" :key="index" :label="item" :value="index" />
            </el-select>
          </el-form-item>
          <el-form-item label="学院" prop="college">
            <el-input v-model="userDetail.college" />
          </el-form-item>
          <el-form-item label="所属二级学院" prop="secondCollege">
            <el-input v-model="userDetail.secondCollege" />
          </el-form-item>
          <el-form-item label="所属班级" prop="classBanji">
            <el-input v-model="userDetail.classBanji" />
          </el-form-item>
          <el-form-item label="所属专业" prop="major">
            <el-input v-model="userDetail.major" />
          </el-form-item>
          <el-form-item label="专业方向" prop="majorDirection">
            <el-input v-model="userDetail.majorDirection" />
          </el-form-item>
          <el-form-item label="生日" prop="birthday">
            <el-input v-model="userDetail.birthday" />
          </el-form-item>
          <el-form-item label="手机号码" prop="mobile">
            <el-input v-model="userDetail.mobile" />
          </el-form-item>
          <el-form-item label="登录密码" prop="password">
            <el-input v-model="userDetail.password" />
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUserUpdate">确定</el-button>
      </div>
    </el-dialog>

    <!-- 义工信息详情对话框 -->
    <el-dialog :visible.sync="infoDialogVisible" title="用户编辑" class="style" :append-to-body="true">
      <el-table
        show-summary
        :summary-method="getSummaries"
        :data="informationList"
      >
        <el-table-column label="学号" prop="username" />
        <el-table-column label="时间" prop="date" />
        <el-table-column label="时长(分钟)" prop="duration" />
        <el-table-column label="描述" prop="desc" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.desc === ''" />
            <span v-else>{{ scope.row.desc }}</span>
          </template>
        </el-table-column>
        <el-table-column label="相关证明照片">
          <template slot-scope="scope">
            <el-image v-for="item in scope.row.img2" :key="item" :src="item" :preview-src-list="scope.row.img2" :lazy="true" style="width: 40px; height: 40px; margin-right: 5px;" />
          </template>
        </el-table-column>
        <tr>
          <td>11</td>
        </tr>

      </el-table>
    </el-dialog>

    <!-- excel上传 -->
    <el-dialog :visible.sync="uploadDialogVisible" title="excel信息" class="style">
      <div class="dialogDiv">
        <div class="exportContain">
          <!--我这里要显示相关数据-->
          <div class="tableContain">
            <el-table
              v-loading="loading"
              :data="tableData"
              element-loading-text="拼命加载中"
              style="width: 100%; margin-top: 10px;"
              border
              highlight-current-row
            >
              <af-table-column v-for="item of tableHeader" :key="item" :prop="item" :label="item" />
            </el-table>
          </div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="uploadDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="uploadToDatabase">上传到数据库</el-button>
      </div>

    </el-dialog>

    <!-- 返回顶部 -->
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

  </div>
</template>

<script>
import { fetchList, updateUser, addUser, deleteUser, listInfoDetail } from '@/api/user'
import Pagination from '@/components/Pagination'
import BackToTop from '@/components/BackToTop'
import XLSX from 'xlsx'

export default {
  name: 'User',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: null,
      informationList: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        college: undefined,
        secondCollege: undefined,
        classBanji: undefined,
        mobile: undefined,
        nickname: undefined,
        sort: 'add_time'
      },
      downloadLoading: false,
      uploadLoading: false,
      genderDic: ['未知', '男', '女'],
      uploadDialogVisible: false,
      /*
      * 用户详情
      * */
      userDetail: {},
      userDialogVisible: false,
      /*
      * 义工信息详情
      * */
      infoDetail: {},
      infoDialogVisible: false,

      /*
      * 上传excel用到的
      * */
      loading: false,
      tableData: [],
      tableHeader: [],
      xlsxJson: [],
      odata: {}
    }
  },
  created() {
    this.getList()
  },
  mouted: {
  },
  methods: {
    /*
    * 获取list
    * */
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    /*
    * 更新用户
    * */
    handleUserUpdate() {
      updateUser(this.userDetail)
        .then((response) => {
          this.userDialogVisible = false
          this.$notify.success({
            title: '成功',
            message: '更新用户成功'
          })
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    /*
    * 删除
    * */
    handleDelete(row) {
      this.$confirm('此操作将删除该学生, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser(row)
          .then(response => {
            this.$notify.success({
              title: '成功',
              message: '删除学生成功'
            })
            this.getList()
          })
          .catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      console.log(this.listQuery.nickname)
      this.getList()
    },
    /*
    * 下载（导出Excel）
    * */
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['学号', '姓名', '手机号码', '性别', '生日']
        const filterVal = ['username', 'nickname', 'mobile', 'gender', 'birthday']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    /*
    * 上传（导出Excel）
    * */
    handleUpload() {
      this.uploadLoading = true
      this.uploadDialogVisible = true
      this.$refs['excel-upload-input'].click()
      this.uploadLoading = false
    },
    handleClick(e) {
      const files = e.target.files
      const fileobj = files[0]
      console.log('文件时:', fileobj)
      const types = fileobj.name.split('.')[1]
      // const types = file.name.split('.')[1]
      const fileType = ['xlsx', 'xlc', 'xlm', 'xls', 'xlt', 'xlw', 'csv'].some(item => item === types)
      this.loading = true
      if (!fileType) {
        this.loading = false
        this.$message('格式错误！请重新选择')
        return
      }
      this.file2Xce(fileobj).then(tabJson => {
        if (tabJson && tabJson.length > 0) {
          this.xlsxJson = tabJson
          this.loading = false
          console.log('数据', this.xlsxJson)

          // xlsxJson就是解析出来的json数据,数据格式如下
          // [
          //   {
          //     sheetName: sheet1
          //     sheet: sheetData
          //   }
          // ]
        }
      })
    },
    file2Xce(file) {
      const self = this
      return new Promise(function(resolve, reject) {
        const reader = new FileReader()
        reader.onload = function(e) {
          const data = e.target.result
          self.wb = XLSX.read(data, { type: 'binary' })
          console.log('binary格式是：', self.wb)
          // 这段代码是处理成表格显示到页面上
          const firstSheetName = self.wb.SheetNames[0]
          const worksheet = self.wb.Sheets[firstSheetName]
          if (worksheet['!ref'] === undefined) {
            self.$message('excel文件为空！请重新选择')
            self.loading = false
            return false
          }
          const header = self.getHeaderRow(worksheet)
          const results = XLSX.utils.sheet_to_json(worksheet)
          self.tableHeader = header
          self.tableData = results
          console.log('表格数据是：', results, self.tableData)
          // this.workbook = XLSX.read(data, {type: 'array'})
          // console.log('array格式是：', this.workbook)
          const result = []
          self.wb.SheetNames.forEach((sheetName) => {
            result.push({
              sheetName: sheetName,
              sheet: XLSX.utils.sheet_to_json(self.wb.Sheets[sheetName])
            })
          })
          resolve(result)
        }
        // reader.readAsBinaryString(file.raw)
        reader.readAsBinaryString(file) // 传统input方法
      })
    },
    getHeaderRow(sheet) {
      const headers = []
      const range = XLSX.utils.decode_range(sheet['!ref'])
      let C
      const R = range.s.r
      /* start in the first row */
      for (C = range.s.c; C <= range.e.c; ++C) { /* walk every column in the range */
        const cell = sheet[XLSX.utils.encode_cell({ c: C, r: R })]
        /* find the cell in the first row */
        let hdr = 'UNKNOWN ' + C // <-- replace with your desired default
        if (cell && cell.t) hdr = XLSX.utils.format_cell(cell)
        headers.push(hdr)
      }
      return headers
    },
    uploadToDatabase() {
      for (var i = 0; i < this.tableData.length; i++) {
        addUser(this.tableData[i])
      }
      this.uploadDialogVisible = false
      this.$notify.success({
        title: '成功',
        message: '添加用户成功'
      })
      this.getList()
    },
    /*
    * 获取基本信息详情
    * */
    handleDetail(row) {
      this.userDetail = row
      console.log(this.userDetail)
      this.userDialogVisible = true
    },
    /*
    * 获取该学生义工信息详情
    * */
    handleInfoDetail(row) {
      this.infoDetail = row
      var username = row.username
      // console.log(username)
      this.infoDialogVisible = true
      listInfoDetail(username).then(response => {
        this.informationList = response.data.data
        console.log(this.informationList)
      })
    },
    /*
    * 合计总时间
    * */
    getSummaries(param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总计'
          return
        }
        const values = (data || []).map(item => Number(item[column.property]))
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
          sums[index] += ' 分钟'
        } else {
          sums[index] = '  '
        }
      })
      return sums
    }
  }
}
</script>

<style lang="scss" scoped>
.dialogDiv {
  height: 400px;
  overflow: auto;
}

.excel-upload-input {
  display: none;
}

.footer {
  float: right;
}
</style>

<style lang="css">
.el-tooltip__popper{font-size: 14px; max-width:30% } /*设置显示隐藏部分内容，按50%显示*/
</style>
