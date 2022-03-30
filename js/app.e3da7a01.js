(function(){var e={4336:function(e,t,r){"use strict";var n=r(9242),a=r(3396);function s(e,t,r,n,s,o){const u=(0,a.up)("NavigationMenu"),i=(0,a.up)("router-view");return(0,a.wg)(),(0,a.iD)(a.HY,null,[(0,a.Wm)(u),(0,a.Wm)(i)],64)}var o=r(7139),u=r(6949);const i=e=>((0,a.dD)("data-v-3644d3d4"),e=e(),(0,a.Cn)(),e),l={class:"container"},d=i((()=>(0,a._)("img",{src:u,alt:"Logo"},null,-1))),c=i((()=>(0,a._)("h3",null,"Subjects",-1))),m={key:1,class:"userInformation"};function p(e,t,r,n,s,u){const i=(0,a.up)("router-link");return(0,a.wg)(),(0,a.iD)("div",l,[(0,a.Wm)(i,{to:"/"},{default:(0,a.w5)((()=>[d])),_:1}),e.loggedIn?((0,a.wg)(),(0,a.j4)(i,{key:0,class:"routerLink",to:"/subjects"},{default:(0,a.w5)((()=>[c])),_:1})):(0,a.kq)("",!0),e.loggedIn?((0,a.wg)(),(0,a.iD)("div",m,[(0,a._)("h3",null,(0,o.zw)(this.$store.state.user.email),1),(0,a._)("button",{class:"logOutBtn",onClick:t[0]||(t[0]=e=>u.logOut())},"Log out")])):(0,a.kq)("",!0)])}var f=r(65);const v={...(0,f.Se)(["loggedIn"])};var g={methods:{logOut(){this.$store.dispatch("logout"),this.$router.push("/login")}},computed:{...v}},b=r(89);const h=(0,b.Z)(g,[["render",p],["__scopeId","data-v-3644d3d4"]]);var w=h,j={created(){const e=localStorage.getItem("user");if(e){const t=JSON.parse(e);this.$store.commit("SET_USER_DATA",t)}document.title="QS99"},components:{NavigationMenu:w},computed:{}};const _=(0,b.Z)(j,[["render",s]]);var S=_,y=r(678);const k=(0,a._)("h3",null,"Active queues",-1),D={class:"cardHolder"},U=(0,a._)("h3",null,"Other subjects",-1),A={class:"cardHolder"};function I(e,t,r,n,s,o){const u=(0,a.up)("SubjectCardActiveQueue"),i=(0,a.up)("SubjectCard");return(0,a.wg)(),(0,a.iD)(a.HY,null,[k,(0,a._)("div",D,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(s.activeSubjects,(e=>((0,a.wg)(),(0,a.j4)(u,{key:e.id,subject:e},null,8,["subject"])))),128))]),U,(0,a._)("div",A,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(s.subjects,(e=>((0,a.wg)(),(0,a.j4)(i,{key:e.id,subject:e},null,8,["subject"])))),128))])],64)}const T=e=>((0,a.dD)("data-v-2f8a1150"),e=e(),(0,a.Cn)(),e),V={class:"card"},O=T((()=>(0,a._)("br",null,null,-1)));function q(e,t,r,n,s,u){const i=(0,a.up)("router-link");return(0,a.wg)(),(0,a.j4)(i,{to:{name:"SubjectDetails",params:{id:r.subject.id}}},{default:(0,a.w5)((()=>[(0,a._)("div",V,[(0,a._)("h1",null,(0,o.zw)(r.subject.code),1),(0,a.Uk)(" "+(0,o.zw)(r.subject.name)+" ",1),O])])),_:1},8,["to"])}var E={props:{subject:{type:Object,required:!0}}};const Z=(0,b.Z)(E,[["render",q],["__scopeId","data-v-2f8a1150"]]);var $=Z;const z=e=>((0,a.dD)("data-v-69f051b6"),e=e(),(0,a.Cn)(),e),W={class:"card"},M=z((()=>(0,a._)("br",null,null,-1)));function C(e,t,r,n,s,u){const i=(0,a.up)("router-link");return(0,a.wg)(),(0,a.j4)(i,{to:{name:"SubjectQueue",params:{id:r.subject.id}}},{default:(0,a.w5)((()=>[(0,a._)("div",W,[(0,a._)("h1",null,(0,o.zw)(r.subject.code),1),(0,a.Uk)(" "+(0,o.zw)(r.subject.name)+" ",1),M])])),_:1},8,["to"])}var Q={props:{subject:{type:Object,required:!0}}};const H=(0,b.Z)(Q,[["render",C],["__scopeId","data-v-69f051b6"]]);var L=H,R=r(6265),N=r.n(R),B={name:"HomeView",components:{SubjectCard:$,SubjectCardActiveQueue:L},created(){document.title="QS99 - Subjects",N().get("http://localhost:8001/subject/getByUser").then((e=>{this.subjects=e}))},data(){return{activeSubjects:[{id:1,code:"IDATT1234",name:"Testfag"},{id:1,code:"IDATT1234",name:"Matematiske metoder 2"}],subjects:[{id:1,code:"IDATT2101",name:"Programmering 1"},{id:1,code:"IDATT2124",name:"Systemutvikling"},{id:1,code:"IDATT9012",name:"Fysikk data"},{id:1,code:"IDATT1011",name:"Operativsystmer"}]}},computed:{...v}};const Y=(0,b.Z)(B,[["render",I]]);var x=Y;const P={id:"nav"},F=(0,a.Uk)("Queue"),J=(0,a.Uk)(" | "),K=(0,a.Uk)("Details"),G=(0,a.Uk)(" | "),X=(0,a.Uk)("Assignments");function ee(e,t,r,n,s,u){const i=(0,a.up)("router-link"),l=(0,a.up)("router-view");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("h1",null,(0,o.zw)(s.subject.code),1),(0,a._)("div",P,[(0,a.Wm)(i,{to:{name:"SubjectQueue"}},{default:(0,a.w5)((()=>[F])),_:1}),J,(0,a.Wm)(i,{to:{name:"SubjectDetails"}},{default:(0,a.w5)((()=>[K])),_:1}),G,(0,a.Wm)(i,{to:{name:"SubjectAssignments"}},{default:(0,a.w5)((()=>[X])),_:1})]),(0,a.Wm)(l,{subject:s.subject},null,8,["subject"])])}var te={props:["id"],created(){document.title="QS99 - "+this.subject.code},data(){return{subject:{id:1,code:"IDATT1234",name:"Tester"}}}};const re=(0,b.Z)(te,[["render",ee]]);var ne=re;function ae(e,t,r,n,s,u){return(0,a.wg)(),(0,a.iD)("h1",null,(0,o.zw)(r.subject.name),1)}var se={props:["subject"]};const oe=(0,b.Z)(se,[["render",ae]]);var ue=oe;const ie=(0,a._)("h1",null,"Queue",-1),le=(0,a._)("button",null,"Join queue",-1);function de(e,t,r,n,s,u){return(0,a.wg)(),(0,a.iD)(a.HY,null,[ie,le,((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(s.queue,((e,t)=>((0,a.wg)(),(0,a.iD)("div",{key:e.lastname,class:"entry"},[(0,a._)("label",null,(0,o.zw)(t+1),1),(0,a.Uk)(" "+(0,o.zw)(e.lastname)+", "+(0,o.zw)(e.firstname)+" | Øving "+(0,o.zw)(e.assignment),1)])))),128))],64)}var ce={props:["event"],data(){return{queue:[{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6},{lastname:"navnesen",firstname:"navn",assignment:6}]}}};const me=(0,b.Z)(ce,[["render",de]]);var pe=me;function fe(e,t){return(0,a.wg)(),(0,a.iD)("div")}const ve={},ge=(0,b.Z)(ve,[["render",fe]]);var be=ge;const he=(0,a._)("h1",null,"Register",-1),we=["disabled"],je={key:0};function _e(e,t,r,s,u,i){const l=(0,a.up)("BaseInput");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("form",{class:"loginForm",onSubmit:t[4]||(t[4]=(0,n.iM)((e=>i.submit()),["prevent"]))},[he,(0,a.Wm)(l,{label:"Last name",type:"lastname",modelValue:s.lastname,"onUpdate:modelValue":t[0]||(t[0]=e=>s.lastname=e),modelModifiers:{lazy:!0},error:s.errors.email},null,8,["modelValue","error"]),(0,a.Wm)(l,{label:"First name",type:"text",modelValue:s.firstname,"onUpdate:modelValue":t[1]||(t[1]=e=>s.firstname=e),modelModifiers:{lazy:!0},error:s.errors.firstname},null,8,["modelValue","error"]),(0,a.Wm)(l,{label:"Email",type:"email",modelValue:s.email,"onUpdate:modelValue":t[2]||(t[2]=e=>s.email=e),modelModifiers:{lazy:!0},error:s.errors.email},null,8,["modelValue","error"]),(0,a.Wm)(l,{label:"Password",type:"password",modelValue:s.password,"onUpdate:modelValue":t[3]||(t[3]=e=>s.password=e),error:s.errors.password},null,8,["modelValue","error"]),(0,a._)("button",{disabled:!i.isValid,type:"submit"},"Submit",8,we),u.error?((0,a.wg)(),(0,a.iD)("p",je,(0,o.zw)(u.error),1)):(0,a.kq)("",!0)],32)])}var Se=r(5708),ye=r(6542),ke={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){this.$store.dispatch("register",{lastname:this.lastname,firstname:this.firstname,email:this.email,password:this.password}).then((()=>{this.$router.push("/subjects")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,ye.Ry)({lastname:(0,ye.Z_)().required(),firstname:(0,ye.Z_)().required(),email:(0,ye.Z_)().email("Invalid email format").required(),password:(0,ye.Z_)().required()}),{errors:t}=(0,Se.cI)({validationSchema:e}),{value:r}=(0,Se.U$)("lastname"),{value:n}=(0,Se.U$)("firstname"),{value:a}=(0,Se.U$)("email"),{value:s}=(0,Se.U$)("password");return{lastname:r,firstname:n,email:a,password:s,errors:t}},computed:{isValid(){return!(this.errors.email||this.errors.password||this.errors.lastname||this.errors.firstname)&&(this.email&&this.password)}}};const De=(0,b.Z)(ke,[["render",_e]]);var Ue=De;const Ae=(0,a._)("h1",null,"Log in",-1),Ie=["disabled"],Te=(0,a._)("br",null,null,-1),Ve=(0,a.Uk)("Dont have a user? Register here!"),Oe={key:0};function qe(e,t,r,s,u,i){const l=(0,a.up)("BaseInput"),d=(0,a.up)("router-link");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,n.iM)((e=>i.submit()),["prevent"]))},[Ae,(0,a.Wm)(l,{label:"Email",type:"email",modelValue:s.email,"onUpdate:modelValue":t[0]||(t[0]=e=>s.email=e),modelModifiers:{lazy:!0},error:s.errors.email},null,8,["modelValue","error"]),(0,a.Wm)(l,{label:"Password",type:"password",modelValue:s.password,"onUpdate:modelValue":t[1]||(t[1]=e=>s.password=e),error:s.errors.password},null,8,["modelValue","error"]),(0,a._)("button",{disabled:!i.isValid,type:"submit"},"Log in",8,Ie),Te,(0,a.Wm)(d,{to:"register"},{default:(0,a.w5)((()=>[Ve])),_:1}),u.error?((0,a.wg)(),(0,a.iD)("p",Oe,(0,o.zw)(s.errors),1)):(0,a.kq)("",!0)],32)])}var Ee={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){console.log(this.email),this.$store.dispatch("login",{email:this.email,password:this.password}).then((()=>{console.log("yep i live"),this.$router.push("/subjects")})).catch((e=>{this.error=e}))}},setup(){const e=(0,ye.Ry)({email:(0,ye.Z_)().email("Invalid email format").required(),password:(0,ye.Z_)().required()}),{errors:t}=(0,Se.cI)({validationSchema:e}),{value:r}=(0,Se.U$)("email"),{value:n}=(0,Se.U$)("password");return{email:r,password:n,errors:t}},computed:{isValid(){return!this.errors.email&&!this.errors.password&&(this.email&&this.password)}}};const Ze=(0,b.Z)(Ee,[["render",qe]]);var $e=Ze;const ze=(0,a.Uk)(" Admin page "),We={id:"nav"},Me=(0,a.Uk)("Students"),Ce=(0,a.Uk)(" | "),Qe=(0,a.Uk)("Subjects");function He(e,t){const r=(0,a.up)("router-link"),n=(0,a.up)("router-view");return(0,a.wg)(),(0,a.iD)(a.HY,null,[ze,(0,a._)("div",We,[(0,a.Wm)(r,{to:{name:"students"}},{default:(0,a.w5)((()=>[Me])),_:1}),Ce,(0,a.Wm)(r,{to:{name:"subjects"}},{default:(0,a.w5)((()=>[Qe])),_:1})]),(0,a.Wm)(n,{subject:e.subject},null,8,["subject"])],64)}const Le={},Re=(0,b.Z)(Le,[["render",He]]);var Ne=Re;const Be=(0,a._)("h3",null,"All students",-1),Ye=(0,a.Uk)(" hello ");function xe(e,t,r,n,s,o){return(0,a.wg)(),(0,a.iD)(a.HY,null,[Be,Ye],64)}var Pe={name:"HomeView",components:{},created(){document.title="QS99 - Students",N().get("http://localhost:8001/admin/getUsers").then((e=>{this.students=e}))},data(){return{students:null}},computed:{...v}};const Fe=(0,b.Z)(Pe,[["render",xe]]);var Je=Fe;const Ke={},Ge=Ke;var Xe=Ge;const et=(0,f.MT)({state:{user:null,role:""},getters:{loggedIn(e){return!!e.user},role(e){return e.role}},mutations:{SET_USER_DATA(e,t){e.user=t,localStorage.setItem("user",JSON.stringify(t)),N().defaults.headers.common.authorization="Bearer "+t.token},CLEAR_USER_DATA(){localStorage.removeItem("user"),location.reload()}},actions:{login({commit:e},t){return N().post("http://localhost:8001/user/login",null,{params:{email:t.email,password:t.password}}).then((t=>{e("SET_USER_DATA",t.data)}))},register({commit:e},t){return N().post("http://localhost:8001/user/register",null,{params:{lastname:t.lastname,firstname:t.firstname,email:t.email,password:t.password}}).then((()=>{e("SET_USER_DATA",t)}))},logout({commit:e}){e("CLEAR_USER_DATA")}},modules:{}});var tt=et;const rt=[{path:"/",redirect:"/subjects"},{path:"/subjects",name:"subjects",component:x,meta:{requiresAuth:!0}},{path:"/subjects/:id",name:"SubjectLayout",props:!0,component:ne,meta:{requiresAuth:!0},children:[{path:"queue",name:"SubjectQueue",component:pe},{path:"details",name:"SubjectDetails",component:ue},{path:"assignments",name:"SubjectAssignments",component:be}]},{path:"/login",name:"login",component:$e},{path:"/register",name:"register",component:Ue},{path:"/admin",name:"admin",props:!0,component:Ne,meta:{requiresAuth:!0},children:[{path:"students",name:"students",component:Je},{path:"allsubjects",name:"allsubjects",component:Xe}]}],nt=(0,y.p7)({history:(0,y.r5)(),routes:rt});nt.beforeEach(((e,t,r)=>{const n=["/login","/register"],a=n.includes(e.path),s=["/admin"],o=s.includes(e.path),u=["/subjects"],i=u.includes(e.path),l=localStorage.getItem("user"),d="ADMIN"===tt.getters.role;return o&&!l||i&&!l?r("/login"):a&&l&&!d||o&&!d&&l?r("/subjects"):i&&d&&l||a&&d&&l?r("/admin"):r()}));var at=nt,st=r(4609),ot=r.n(st),ut=r(8420),it=r.n(ut);const lt=r(1917),dt=(0,n.ri)(S);lt.keys().forEach((e=>{const t=lt(e),r=ot()(it()(e.replace(/^\.\/(.*)\.\w+$/,"$1")));dt.component(r,t.default||t)})),dt.use(tt).use(at).mount("#app")},4524:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return l}});var n=r(3396);const a=["id"];function s(e,t,r,s,o,u){return(0,n.wg)(),(0,n.iD)("p",{"aria-live":"assertive",class:"errorMessage",id:r.id},[(0,n.WI)(e.$slots,"default",{},void 0,!0)],8,a)}var o={props:{id:{type:[String,Number],required:!0}}},u=r(89);const i=(0,u.Z)(o,[["render",s],["__scopeId","data-v-b3a6cd26"]]);var l=i},3211:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return f}});var n=r(3396),a=r(7139);const s={class:"container"},o=["for"],u=["id","value","placeholder","aria-describedby","aria-invalid"];function i(e,t,r,i,l,d){const c=(0,n.up)("BaseErrorMessage");return(0,n.wg)(),(0,n.iD)("div",s,[r.label?((0,n.wg)(),(0,n.iD)("label",{key:0,for:i.uuid},(0,a.zw)(r.label),9,o)):(0,n.kq)("",!0),(0,n._)("input",(0,n.dG)({class:"field"},e.$attrs,{onInput:t[0]||(t[0]=t=>e.$emit("update:modelValue",t.target.value)),id:i.uuid,value:r.modelValue,placeholder:r.label,"aria-describedby":r.error?`${i.uuid}-error`:null,"aria-invalid":!!r.error,class:{error:r.error}}),null,16,u),(0,n.Wm)(c,{id:`${i.uuid}-error`},{default:(0,n.w5)((()=>[(0,n.Uk)((0,a.zw)(r.error),1)])),_:1},8,["id"])])}let l=0;function d(){const e=()=>(l++,l);return{getID:e}}var c={props:{label:{type:String,default:""},error:{type:String,default:""},modelValue:{type:[String,Number],default:""}},setup(){const e=d().getID();return{uuid:e}}},m=r(89);const p=(0,m.Z)(c,[["render",i],["__scopeId","data-v-252fad5b"]]);var f=p},1917:function(e,t,r){var n={"./BaseErrorMessage.vue":4524,"./BaseInput.vue":3211};function a(e){var t=s(e);return r(t)}function s(e){if(!r.o(n,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return n[e]}a.keys=function(){return Object.keys(n)},a.resolve=s,e.exports=a,a.id=1917},6949:function(e,t,r){"use strict";e.exports=r.p+"img/logo.d1e80654.png"}},t={};function r(n){var a=t[n];if(void 0!==a)return a.exports;var s=t[n]={id:n,loaded:!1,exports:{}};return e[n](s,s.exports,r),s.loaded=!0,s.exports}r.m=e,function(){var e=[];r.O=function(t,n,a,s){if(!n){var o=1/0;for(d=0;d<e.length;d++){n=e[d][0],a=e[d][1],s=e[d][2];for(var u=!0,i=0;i<n.length;i++)(!1&s||o>=s)&&Object.keys(r.O).every((function(e){return r.O[e](n[i])}))?n.splice(i--,1):(u=!1,s<o&&(o=s));if(u){e.splice(d--,1);var l=a();void 0!==l&&(t=l)}}return t}s=s||0;for(var d=e.length;d>0&&e[d-1][2]>s;d--)e[d]=e[d-1];e[d]=[n,a,s]}}(),function(){r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,{a:t}),t}}(),function(){r.d=function(e,t){for(var n in t)r.o(t,n)&&!r.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:t[n]})}}(),function(){r.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){r.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){r.p="/"}(),function(){var e={143:0};r.O.j=function(t){return 0===e[t]};var t=function(t,n){var a,s,o=n[0],u=n[1],i=n[2],l=0;if(o.some((function(t){return 0!==e[t]}))){for(a in u)r.o(u,a)&&(r.m[a]=u[a]);if(i)var d=i(r)}for(t&&t(n);l<o.length;l++)s=o[l],r.o(e,s)&&e[s]&&e[s][0](),e[s]=0;return r.O(d)},n=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}();var n=r.O(void 0,[998],(function(){return r(4336)}));n=r.O(n)})();
//# sourceMappingURL=app.e3da7a01.js.map