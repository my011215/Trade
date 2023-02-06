import { createRouter, createWebHistory, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: () => import('@/views/Main.vue'),
    meta: { title: '自述文件' },
    children: [
      {
        path:'/',
        component: () => import('@/views/ProductPage.vue'),
        children: [
          {
            path:'',
            component: () => import('@/views/Products/CurrencyPage.vue'),
          },
          {
            path:'/cu',
            component: () => import('@/views/Products/CurrencyPage.vue'),
          },
          {
            path:'/cn',
            component: () => import('@/views/Products/AStockPage.vue'),
          },
          {
            path:'/hk',
            component: () => import('@/views/Products/HKStockPage.vue'),
          },
          {
            path:'/us',
            component: () => import('@/views/Products/USStockPage.vue'),
          },
          {
            path:'/fg',
            component: () => import('@/views/Products/FutureGoodsPage.vue'),
          },
        ]
      },
      {
        path:'/community',
        name:'community',
        component: () => import('@/views/CommunityPage.vue'),
      },
      {
        path:'/trade',
        name:'trade',
        component: () => import('@/views/TradePage.vue'),
      },
      {
        path:'/account',
        name:'account',
        component: () => import('@/views/AccountPage.vue'),
        children: [
          {
            path:'',
            component: () => import('@/views/TradeDetails/PositionPage.vue'),
          },
          {
            path:'/cc',
            component: () => import('@/views/TradeDetails/PositionPage.vue'),
          },
          {
            path:'/dc',
            component: () => import('@/views/TradeDetails/AdjustPage.vue'),
          },
          {
            path:'/ls',
            component: () => import('@/views/TradeDetails/HistoryPage.vue'),
          }
        ]
      },
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/LoginPage.vue'),
    meta: { title: '自述文件' }
  }
]

const router = createRouter({
  // history: createWebHistory(process.env.BASE_URL),
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router