import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './Home.vue'
import Doctor from './doctor/Doctor.vue'
import Nurse from './nurse/Nurse.vue'
import LogIn from "./LogIn.vue";
import Mobile from "@/routes/mobile/Mobile.vue";
import MobileRegister from "@/routes/mobile/Register.vue";
import MobileDoctor from "@/routes/mobile/Doctor.vue";
import ImageEditor from "@/routes/doctor/ImageEditor.vue";
// import NotFound from './NotFound'

Vue.use(VueRouter)

export default new VueRouter({
    scrollBehavior() {
        return {x: 0, y: 0}
    },
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/logIn/',
            component: LogIn
        },
        {
            path: '/',
            component: Home
        },
        {
            path: '/doctor/',
            component: Doctor
        },
        {
            path: '/doctor/editor',
            component: ImageEditor
        },
        {
            path: '/nurse/',
            component: Nurse
        },
        {
            path: '/m.home/',
            component: Mobile
        },
        {
            path: '/m.register/',
            component: MobileRegister
        },
        {
            path: '/m.doctor/',
            component: MobileDoctor
        },
        // {
        //   path: '/:notFound(.*)',
        //   component: NotFound
        // }
    ]
})