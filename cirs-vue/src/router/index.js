import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/main/inmain/Home'
import CampusInfo from '@/components/main/inmain/CampusInfo'
import UserManage from '@/components/main/inmain/admin/UserManage'
import StudentManage from '@/components/main/inmain/teacher/StudentManage'
import InfoManage from '@/components/main/inmain/teacher/InfoManage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: "/home"
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/campusinfo',
      name: 'CampusInfo',
      component: CampusInfo
    },
    {
      path: '/admin/usermanage',
      name: 'UserManage',
      component: UserManage
    },
    {
      path: '/teacher/studentmanage',
      name: 'StudentManage',
      component: StudentManage
    },
    {
      path: '/teacher/infomanage',
      name: 'InfoManage',
      component: InfoManage
    }
    // {
    //   path: '/',
    //   name: '',
    //   component:
    // },
  ]
})
