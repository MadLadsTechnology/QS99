(function(){var e={6074:function(e,t,r){"use strict";var n=r(9242),s=r(3396);function a(e,t,r,n,a,o){const u=(0,s.up)("NavigationMenu"),l=(0,s.up)("router-view");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s.Wm)(u),(0,s.Wm)(l)],64)}var o=r(7139),u=r(6949);const l=e=>((0,s.dD)("data-v-3644d3d4"),e=e(),(0,s.Cn)(),e),i={class:"container"},c=l((()=>(0,s._)("img",{src:u,alt:"Logo"},null,-1))),d=l((()=>(0,s._)("h3",null,"Subjects",-1))),m={key:1,class:"userInformation"};function p(e,t,r,n,a,u){const l=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",i,[(0,s.Wm)(l,{to:"/"},{default:(0,s.w5)((()=>[c])),_:1}),e.loggedIn?((0,s.wg)(),(0,s.j4)(l,{key:0,class:"routerLink",to:"/subjects"},{default:(0,s.w5)((()=>[d])),_:1})):(0,s.kq)("",!0),e.loggedIn?((0,s.wg)(),(0,s.iD)("div",m,[(0,s._)("h3",null,(0,o.zw)(this.$store.state.user.email),1),(0,s._)("button",{class:"logOutBtn",onClick:t[0]||(t[0]=e=>u.logOut())},"Log out")])):(0,s.kq)("",!0)])}var h=r(65);const f={...(0,h.Se)(["loggedIn"])};var b={methods:{logOut(){this.$store.dispatch("logout"),this.$router.push("/login")}},computed:{...f}},g=r(89);const v=(0,g.Z)(b,[["render",p],["__scopeId","data-v-3644d3d4"]]);var w=v,j={created(){const e=localStorage.getItem("user");if(e){const t=JSON.parse(e);this.$store.commit("SET_USER_DATA",t)}document.title="QS99"},components:{NavigationMenu:w},computed:{}};const _=(0,g.Z)(j,[["render",a]]);var S=_,y=r(678);const k=(0,s._)("h3",null,"Active queues",-1),D={class:"cardHolder"},U=(0,s._)("h3",null,"Other subjects",-1),I={class:"cardHolder"};function A(e,t,r,n,a,o){const u=(0,s.up)("SubjectCard");return(0,s.wg)(),(0,s.iD)(s.HY,null,[k,(0,s._)("div",D,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(o.subjects.active,(e=>((0,s.wg)(),(0,s.j4)(u,{key:parseInt(e.id),subject:e},null,8,["subject"])))),128))]),U,(0,s._)("div",I,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(o.subjects.inActive,(e=>((0,s.wg)(),(0,s.j4)(u,{key:parseInt(e.id),subject:e},null,8,["subject"])))),128))])],64)}const V=e=>((0,s.dD)("data-v-69ee9ede"),e=e(),(0,s.Cn)(),e),O={class:"card"},q=V((()=>(0,s._)("br",null,null,-1)));function E(e,t,r,n,a,u){const l=(0,s.up)("router-link");return(0,s.wg)(),(0,s.j4)(l,{to:{name:"SubjectLayout",params:{id:r.subject.id}}},{default:(0,s.w5)((()=>[(0,s._)("div",O,[(0,s._)("h1",null,(0,o.zw)(r.subject.subjectCode),1),(0,s.Uk)(" "+(0,o.zw)(r.subject.subjectName)+" "+(0,o.zw)(r.subject.id)+" ",1),q])])),_:1},8,["to"])}var $={props:{subject:{type:Object,required:!0}}};const z=(0,g.Z)($,[["render",E],["__scopeId","data-v-69ee9ede"]]);var Z=z,W=r(6265),M=r.n(W),T={name:"HomeView",components:{SubjectCard:Z},async created(){document.title="QS99 - Subjects",await M().get("http://localhost:8001/subject/getByUser").then((e=>{this.allSubjects=e.data}))},data(){return{allSubjects:[]}},computed:{...f,subjects:function(){const e=[],t=[];for(let r=0;r<this.allSubjects.length;r++)console.log(this.allSubjects[r]),!0===this.allSubjects[r].queueActive?e.push(this.allSubjects[r]):t.push(this.allSubjects[r]);return{active:e,inActive:t}}}};const C=(0,g.Z)(T,[["render",A]]);var H=C;const L={id:"nav"},R=(0,s.Uk)("Queue"),N=(0,s.Uk)(" | "),Q=(0,s.Uk)("Details"),Y=(0,s.Uk)(" | "),B=(0,s.Uk)("Assignments");function x(e,t,r,n,a,u){const l=(0,s.up)("router-link"),i=(0,s.up)("router-view");return(0,s.wg)(),(0,s.iD)("div",null,[(0,s._)("h1",null,(0,o.zw)(a.subject.subjectCode),1),(0,s._)("div",L,[(0,s.Wm)(l,{to:{name:"SubjectQueue"}},{default:(0,s.w5)((()=>[R])),_:1}),N,(0,s.Wm)(l,{to:{name:"SubjectDetails"}},{default:(0,s.w5)((()=>[Q])),_:1}),Y,(0,s.Wm)(l,{to:{name:"SubjectAssignments"}},{default:(0,s.w5)((()=>[B])),_:1})]),(0,s.Wm)(i,{subject:a.subject},null,8,["subject"])])}var P={props:["id"],data(){return{subject:null}},async created(){await M().get("http://localhost:8001/subject/getSubject",{params:{subjectId:parseInt(this.id)}}).then((e=>{this.subject=e.data})).catch((e=>{alert(e)})),document.title="QS99 - "+this.subject.subjectCode}};const F=(0,g.Z)(P,[["render",x]]);var J=F;function K(e,t,r,n,a,u){return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("h1",null,(0,o.zw)(r.subject.subjectName),1),(0,s._)("h3",null,(0,o.zw)(r.subject.subjectDescription),1)],64)}var G={props:["subject"]};const X=(0,g.Z)(G,[["render",K]]);var ee=X;const te=(0,s._)("h1",null,"Queue",-1),re=(0,s._)("button",null,"Join queue",-1);function ne(e,t,r,n,a,u){return(0,s.wg)(),(0,s.iD)(s.HY,null,[te,re,((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(a.queue,((e,t)=>((0,s.wg)(),(0,s.iD)("div",{key:e.lastname,class:"entry"},[(0,s._)("label",null,(0,o.zw)(t+1),1),(0,s.Uk)(" "+(0,o.zw)(e.lastname)+", "+(0,o.zw)(e.firstname)+" | Øving "+(0,o.zw)(e.assignment),1)])))),128))],64)}var se={props:["subject"],created(){M().get("http://localhost:8001/queue",null,{params:{id:this.subject.id}})},methods:{},data(){return{queue:[{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6}]}}};const ae=(0,g.Z)(se,[["render",ne]]);var oe=ae;function ue(e,t){return(0,s.wg)(),(0,s.iD)("div")}const le={},ie=(0,g.Z)(le,[["render",ue]]);var ce=ie;const de=(0,s._)("h1",null,"Register",-1),me=["disabled"],pe={key:0};function he(e,t,r,a,u,l){const i=(0,s.up)("BaseInput");return(0,s.wg)(),(0,s.iD)("div",null,[(0,s._)("form",{class:"loginForm",onSubmit:t[4]||(t[4]=(0,n.iM)((e=>l.submit()),["prevent"]))},[de,(0,s.Wm)(i,{label:"Last name",type:"lastname",modelValue:a.lastname,"onUpdate:modelValue":t[0]||(t[0]=e=>a.lastname=e),modelModifiers:{lazy:!0},error:a.errors.email},null,8,["modelValue","error"]),(0,s.Wm)(i,{label:"First name",type:"text",modelValue:a.firstname,"onUpdate:modelValue":t[1]||(t[1]=e=>a.firstname=e),modelModifiers:{lazy:!0},error:a.errors.firstname},null,8,["modelValue","error"]),(0,s.Wm)(i,{label:"Email",type:"email",modelValue:a.email,"onUpdate:modelValue":t[2]||(t[2]=e=>a.email=e),modelModifiers:{lazy:!0},error:a.errors.email},null,8,["modelValue","error"]),(0,s.Wm)(i,{label:"Password",type:"password",modelValue:a.password,"onUpdate:modelValue":t[3]||(t[3]=e=>a.password=e),error:a.errors.password},null,8,["modelValue","error"]),(0,s._)("button",{disabled:!l.isValid,type:"submit"},"Submit",8,me),u.error?((0,s.wg)(),(0,s.iD)("p",pe,(0,o.zw)(u.error),1)):(0,s.kq)("",!0)],32)])}var fe=r(5708),be=r(6542),ge={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){this.$store.dispatch("register",{lastname:this.lastname,firstname:this.firstname,email:this.email,password:this.password}).then((()=>{this.$router.push("/subjects")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,be.Ry)({lastname:(0,be.Z_)().required(),firstname:(0,be.Z_)().required(),email:(0,be.Z_)().email("Invalid email format").required(),password:(0,be.Z_)().required()}),{errors:t}=(0,fe.cI)({validationSchema:e}),{value:r}=(0,fe.U$)("lastname"),{value:n}=(0,fe.U$)("firstname"),{value:s}=(0,fe.U$)("email"),{value:a}=(0,fe.U$)("password");return{lastname:r,firstname:n,email:s,password:a,errors:t}},computed:{isValid(){return!(this.errors.email||this.errors.password||this.errors.lastname||this.errors.firstname)&&(this.email&&this.password)}}};const ve=(0,g.Z)(ge,[["render",he]]);var we=ve;const je=(0,s._)("h1",null,"Log in",-1),_e=["disabled"],Se=(0,s._)("br",null,null,-1),ye=(0,s.Uk)("Dont have a user? Register here!"),ke={key:0};function De(e,t,r,a,u,l){const i=(0,s.up)("BaseInput"),c=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",null,[(0,s._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,n.iM)((e=>l.submit()),["prevent"]))},[je,(0,s.Wm)(i,{label:"Email",type:"email",modelValue:a.email,"onUpdate:modelValue":t[0]||(t[0]=e=>a.email=e),modelModifiers:{lazy:!0},error:a.errors.email},null,8,["modelValue","error"]),(0,s.Wm)(i,{label:"Password",type:"password",modelValue:a.password,"onUpdate:modelValue":t[1]||(t[1]=e=>a.password=e),error:a.errors.password},null,8,["modelValue","error"]),(0,s._)("button",{disabled:!l.isValid,type:"submit"},"Log in",8,_e),Se,(0,s.Wm)(c,{to:"register"},{default:(0,s.w5)((()=>[ye])),_:1}),u.error?((0,s.wg)(),(0,s.iD)("p",ke,(0,o.zw)(a.errors),1)):(0,s.kq)("",!0)],32)])}var Ue={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){console.log(this.email),this.$store.dispatch("login",{email:this.email,password:this.password}).then((()=>{console.log("yep i live"),this.$router.push("/subjects")})).catch((e=>{this.error=e}))}},setup(){const e=(0,be.Ry)({email:(0,be.Z_)().email("Invalid email format").required(),password:(0,be.Z_)().required()}),{errors:t}=(0,fe.cI)({validationSchema:e}),{value:r}=(0,fe.U$)("email"),{value:n}=(0,fe.U$)("password");return{email:r,password:n,errors:t}},computed:{isValid(){return!this.errors.email&&!this.errors.password&&(this.email&&this.password)}}};const Ie=(0,g.Z)(Ue,[["render",De]]);var Ae=Ie;const Ve=(0,s.Uk)(" Admin page "),Oe={id:"nav"},qe=(0,s.Uk)("Students"),Ee=(0,s.Uk)(" | "),$e=(0,s.Uk)("Subjects");function ze(e,t){const r=(0,s.up)("router-link"),n=(0,s.up)("router-view");return(0,s.wg)(),(0,s.iD)(s.HY,null,[Ve,(0,s._)("div",Oe,[(0,s.Wm)(r,{to:{name:"students"}},{default:(0,s.w5)((()=>[qe])),_:1}),Ee,(0,s.Wm)(r,{to:{name:"subjects"}},{default:(0,s.w5)((()=>[$e])),_:1})]),(0,s.Wm)(n,{subject:e.subject},null,8,["subject"])],64)}const Ze={},We=(0,g.Z)(Ze,[["render",ze]]);var Me=We;const Te=(0,s._)("h3",null,"All students",-1),Ce=(0,s.Uk)(" hello ");function He(e,t,r,n,a,o){return(0,s.wg)(),(0,s.iD)(s.HY,null,[Te,Ce],64)}var Le={name:"HomeView",components:{},created(){document.title="QS99 - Students",M().get("http://localhost:8001/admin/getUsers").then((e=>{this.students=e}))},data(){return{students:null}},computed:{...f}};const Re=(0,g.Z)(Le,[["render",He]]);var Ne=Re;const Qe={},Ye=Qe;var Be=Ye;const xe=(0,h.MT)({state:{user:null,role:""},getters:{loggedIn(e){return!!e.user},role(e){return e.role}},mutations:{SET_USER_DATA(e,t){e.user=t,localStorage.setItem("user",JSON.stringify(t)),M().defaults.headers.common.authorization="Bearer "+t.token},CLEAR_USER_DATA(){localStorage.removeItem("user"),location.reload()}},actions:{login({commit:e},t){return M().post("http://localhost:8001/user/login",null,{params:{email:t.email,password:t.password}}).then((t=>{e("SET_USER_DATA",t.data)}))},register({commit:e},t){return M().post("http://localhost:8001/user/register",null,{params:{lastname:t.lastname,firstname:t.firstname,email:t.email,password:t.password}}).then((()=>{e("SET_USER_DATA",t)}))},logout({commit:e}){e("CLEAR_USER_DATA")}},modules:{}});var Pe=xe;const Fe=[{path:"/",redirect:"/subjects"},{path:"/subjects",name:"subjects",component:H,meta:{requiresAuth:!0}},{path:"/subjects/:id",name:"SubjectLayout",props:!0,component:J,meta:{requiresAuth:!0},children:[{path:"queue",name:"SubjectQueue",component:oe},{path:"details",name:"SubjectDetails",component:ee},{path:"assignments",name:"SubjectAssignments",component:ce}]},{path:"/login",name:"login",component:Ae},{path:"/register",name:"register",component:we},{path:"/admin",name:"admin",props:!0,component:Me,meta:{requiresAuth:!0},children:[{path:"students",name:"students",component:Ne},{path:"allsubjects",name:"allsubjects",component:Be}]}],Je=(0,y.p7)({history:(0,y.r5)(),routes:Fe});Je.beforeEach(((e,t,r)=>{const n=["/login","/register"],s=n.includes(e.path),a=["/admin"],o=a.includes(e.path),u=["/subjects"],l=u.includes(e.path),i=localStorage.getItem("user"),c="ADMIN"===Pe.getters.role;return o&&!i||l&&!i?r("/login"):s&&i&&!c||o&&!c&&i?r("/subjects"):l&&c&&i||s&&c&&i?r("/admin"):r()}));var Ke=Je,Ge=r(4609),Xe=r.n(Ge),et=r(8420),tt=r.n(et);const rt=r(1917),nt=(0,n.ri)(S);rt.keys().forEach((e=>{const t=rt(e),r=Xe()(tt()(e.replace(/^\.\/(.*)\.\w+$/,"$1")));nt.component(r,t.default||t)})),nt.use(Pe).use(Ke).mount("#app")},4524:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return i}});var n=r(3396);const s=["id"];function a(e,t,r,a,o,u){return(0,n.wg)(),(0,n.iD)("p",{"aria-live":"assertive",class:"errorMessage",id:r.id},[(0,n.WI)(e.$slots,"default",{},void 0,!0)],8,s)}var o={props:{id:{type:[String,Number],required:!0}}},u=r(89);const l=(0,u.Z)(o,[["render",a],["__scopeId","data-v-b3a6cd26"]]);var i=l},3211:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return h}});var n=r(3396),s=r(7139);const a={class:"container"},o=["for"],u=["id","value","placeholder","aria-describedby","aria-invalid"];function l(e,t,r,l,i,c){const d=(0,n.up)("BaseErrorMessage");return(0,n.wg)(),(0,n.iD)("div",a,[r.label?((0,n.wg)(),(0,n.iD)("label",{key:0,for:l.uuid},(0,s.zw)(r.label),9,o)):(0,n.kq)("",!0),(0,n._)("input",(0,n.dG)({class:"field"},e.$attrs,{onInput:t[0]||(t[0]=t=>e.$emit("update:modelValue",t.target.value)),id:l.uuid,value:r.modelValue,placeholder:r.label,"aria-describedby":r.error?`${l.uuid}-error`:null,"aria-invalid":!!r.error,class:{error:r.error}}),null,16,u),(0,n.Wm)(d,{id:`${l.uuid}-error`},{default:(0,n.w5)((()=>[(0,n.Uk)((0,s.zw)(r.error),1)])),_:1},8,["id"])])}let i=0;function c(){const e=()=>(i++,i);return{getID:e}}var d={props:{label:{type:String,default:""},error:{type:String,default:""},modelValue:{type:[String,Number],default:""}},setup(){const e=c().getID();return{uuid:e}}},m=r(89);const p=(0,m.Z)(d,[["render",l],["__scopeId","data-v-252fad5b"]]);var h=p},1917:function(e,t,r){var n={"./BaseErrorMessage.vue":4524,"./BaseInput.vue":3211};function s(e){var t=a(e);return r(t)}function a(e){if(!r.o(n,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return n[e]}s.keys=function(){return Object.keys(n)},s.resolve=a,e.exports=s,s.id=1917},6949:function(e,t,r){"use strict";e.exports=r.p+"img/logo.d1e80654.png"}},t={};function r(n){var s=t[n];if(void 0!==s)return s.exports;var a=t[n]={id:n,loaded:!1,exports:{}};return e[n](a,a.exports,r),a.loaded=!0,a.exports}r.m=e,function(){var e=[];r.O=function(t,n,s,a){if(!n){var o=1/0;for(c=0;c<e.length;c++){n=e[c][0],s=e[c][1],a=e[c][2];for(var u=!0,l=0;l<n.length;l++)(!1&a||o>=a)&&Object.keys(r.O).every((function(e){return r.O[e](n[l])}))?n.splice(l--,1):(u=!1,a<o&&(o=a));if(u){e.splice(c--,1);var i=s();void 0!==i&&(t=i)}}return t}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[n,s,a]}}(),function(){r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,{a:t}),t}}(),function(){r.d=function(e,t){for(var n in t)r.o(t,n)&&!r.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:t[n]})}}(),function(){r.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){r.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){r.p="/"}(),function(){var e={143:0};r.O.j=function(t){return 0===e[t]};var t=function(t,n){var s,a,o=n[0],u=n[1],l=n[2],i=0;if(o.some((function(t){return 0!==e[t]}))){for(s in u)r.o(u,s)&&(r.m[s]=u[s]);if(l)var c=l(r)}for(t&&t(n);i<o.length;i++)a=o[i],r.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return r.O(c)},n=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}();var n=r.O(void 0,[998],(function(){return r(6074)}));n=r.O(n)})();
//# sourceMappingURL=app.7764d073.js.map