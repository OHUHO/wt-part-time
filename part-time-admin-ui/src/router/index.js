import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '仪表盘', icon: 'dashboard' }
    }]
  },
  {
    path: '/carousel',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Carousel',
        component: () => import('@/views/carousel/index'),
        meta: { title: '轮播图', icon: 'form' }
      }
    ]
  },
  {
    path: '/jobs',
    component: Layout,
    redirect: '/jobs/job',
    name: 'Jobs',
    meta: { title: '兼职管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'job',
        name: 'Job',
        component: () => import('@/views/job/job/index'),
        meta: { title: '兼职信息', icon: 'table' }
      },
      {
        path: 'type',
        name: 'Type',
        component: () => import('@/views/job/type/index'),
        meta: { title: '兼职种类', icon: 'tree' }
      },
    ]
  },
  {
    path: '/business',
    component: Layout,
    redirect: '/business/info',
    name: 'Business',
    meta: { title: '商家管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'info',
        name: 'Info',
        component: () => import('@/views/business/info/index'),
        meta: { title: '商家信息', icon: 'table' }
      },
      {
        path: 'pending',
        name: 'Pending',
        component: () => import('@/views/business/pending/index'),
        meta: { title: '资质审核', icon: 'tree' }
      },
    ]
  },
  {
    path: '/experience',
    component: Layout,
    children: [
      {
        path: 'info',
        name: 'Experience',
        component: () => import('@/views/experience/index'),
        meta: { title: '经验管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/index'),
        meta: { title: '用户管理', icon: 'form' }
      }
    ]
  },
  { 
    path: '*', 
    redirect: '/404', 
    hidden: true 
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
