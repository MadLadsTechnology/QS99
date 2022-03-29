(function(){var e={7827:function(e,t,r){"use strict";var n=r(9242),o=r(3396);function a(e,t,r,n,a,s){const u=(0,o.up)("NavigationMenu"),i=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o.Wm)(u),(0,o.Wm)(i)],64)}var s=r(7139),u=r(6949);const i=e=>((0,o.dD)("data-v-31544fd3"),e=e(),(0,o.Cn)(),e),l={class:"container"},c=i((()=>(0,o._)("img",{src:u,alt:"Logo"},null,-1))),d=i((()=>(0,o._)("h3",null,"Subjects",-1))),m={key:1,class:"userInformation"};function p(e,t,r,n,a,u){const i=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("div",l,[c,e.loggedIn?((0,o.wg)(),(0,o.j4)(i,{key:0,class:"routerLink",to:"/"},{default:(0,o.w5)((()=>[d])),_:1})):(0,o.kq)("",!0),e.loggedIn?((0,o.wg)(),(0,o.iD)("div",m,[(0,o._)("h3",null,(0,s.zw)(this.$store.state.user.firstname),1),(0,o._)("button",{class:"logOutBtn",onClick:t[0]||(t[0]=e=>u.logOut())},"Log out")])):(0,o.kq)("",!0)])}var f=r(65);const g={...(0,f.Se)(["loggedIn"])};var v={methods:{logOut(){this.$store.dispatch("logout")}},computed:{...g}},h=r(89);const b=(0,h.Z)(v,[["render",p],["__scopeId","data-v-31544fd3"]]);var w=b,_={created(){const e=localStorage.getItem("user");if(e){const t=JSON.parse(e);this.$store.commit("SET_USER_DATA",t)}document.title="QS99"},components:{NavigationMenu:w},computed:{}};const j=(0,h.Z)(_,[["render",a]]);var S=j,y=r(678);const D=(0,o._)("h3",null,"Here are your active subjects",-1),T={class:"cardHolder"};function k(e,t,r,n,a,u){const i=(0,o.up)("SubjectCard");return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o._)("h1",null,'Welcome "'+(0,s.zw)(this.$store.state.user.firstname)+'"',1),D,(0,o._)("div",T,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(a.subjects,(e=>((0,o.wg)(),(0,o.j4)(i,{key:e.id,subject:e},null,8,["subject"])))),128))])],64)}const I={class:"card"};function A(e,t,r,n,a,u){const i=(0,o.up)("router-link");return(0,o.wg)(),(0,o.j4)(i,{to:{name:"SubjectDetails",params:{id:r.subject.id}}},{default:(0,o.w5)((()=>[(0,o._)("div",I,[(0,o._)("h1",null,(0,s.zw)(r.subject.code),1),(0,o.Uk)(" "+(0,s.zw)(r.subject.name),1)])])),_:1},8,["to"])}var O={props:{subject:{type:Object,required:!0}}};const E=(0,h.Z)(O,[["render",A],["__scopeId","data-v-56897820"]]);var U=E,$={name:"HomeView",components:{SubjectCard:U},created(){document.title="QS99 - Subjects"},data(){return{subjects:[{id:1,code:"IDATT1234",name:"Testfag"},{id:1,code:"IDATT1234",name:"Testfag"},{id:1,code:"IDATT1234",name:"Testfag"},{id:1,code:"IDATT1234",name:"Testfag"}]}},computed:{...g}};const Z=(0,h.Z)($,[["render",k]]);var V=Z;const M={id:"nav"},N=(0,o.Uk)("Details"),z=(0,o.Uk)(" | "),W=(0,o.Uk)("Queue"),L=(0,o.Uk)(" | "),q=(0,o.Uk)("Assignments");function C(e,t,r,n,a,u){const i=(0,o.up)("router-link"),l=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("h1",null,(0,s.zw)(a.subject.code),1),(0,o._)("div",M,[(0,o.Wm)(i,{to:{name:"SubjectDetails"}},{default:(0,o.w5)((()=>[N])),_:1}),z,(0,o.Wm)(i,{to:{name:"SubjectQueue"}},{default:(0,o.w5)((()=>[W])),_:1}),L,(0,o.Wm)(i,{to:{name:"SubjectAssignments"}},{default:(0,o.w5)((()=>[q])),_:1})]),(0,o.Wm)(l,{subject:a.subject},null,8,["subject"])])}var R={props:["id"],created(){document.title="QS99 - "+this.subject.code},data(){return{subject:{id:1,code:"IDATT1234",name:"Tester"}}}};const Q=(0,h.Z)(R,[["render",C]]);var H=Q;function x(e,t,r,n,a,u){return(0,o.wg)(),(0,o.iD)("h1",null,(0,s.zw)(r.subject.name),1)}var B={props:["subject"]};const P=(0,h.Z)(B,[["render",x]]);var F=P;function Y(e,t,r,n,a,s){return(0,o.wg)(),(0,o.iD)("h1",null,"Queue")}var J={props:["event"]};const G=(0,h.Z)(J,[["render",Y]]);var K=G;function X(e,t){return(0,o.wg)(),(0,o.iD)("div")}const ee={},te=(0,h.Z)(ee,[["render",X]]);var re=te;function ne(e,t,r,n,a,s){return(0,o.wg)(),(0,o.iD)("div")}var oe={data(){return{name:"",email:"",password:""}},methods:{register(){this.$store.dispatch("register",{firstName:this.firstName,lastName:this.lastName,email:this.email,password:this.password}).then((()=>{this.$router.push({name:"HomeView"})})).catch((e=>{console.log(e)}))}}};const ae=(0,h.Z)(oe,[["render",ne]]);var se=ae;const ue=(0,o._)("h1",null,"Log in",-1),ie=["disabled"],le={key:0};function ce(e,t,r,a,u,i){const l=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,n.iM)((e=>i.submit()),["prevent"]))},[ue,(0,o.Wm)(l,{label:"Email",type:"email",modelValue:a.email,"onUpdate:modelValue":t[0]||(t[0]=e=>a.email=e),modelModifiers:{lazy:!0},error:a.errors.email},null,8,["modelValue","error"]),(0,o.Wm)(l,{label:"Password",type:"password",modelValue:a.password,"onUpdate:modelValue":t[1]||(t[1]=e=>a.password=e),error:a.errors.password},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!i.isValid,type:"submit"},"Log in",8,ie),u.error?((0,o.wg)(),(0,o.iD)("p",le,(0,s.zw)(u.error),1)):(0,o.kq)("",!0)],32)])}var de=r(5708),me=r(6542),pe={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){console.log(this.email),this.$store.dispatch("login",{email:this.email,password:this.password}).then((()=>{console.log("yep i live"),this.$router.push("/")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,me.Ry)({email:(0,me.Z_)().email("Invalid email format").required(),password:(0,me.Z_)().required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:r}=(0,de.U$)("email"),{value:n}=(0,de.U$)("password");return{email:r,password:n,errors:t}},computed:{isValid(){return!this.errors.email&&!this.errors.password&&(this.email&&this.password)}}};const fe=(0,h.Z)(pe,[["render",ce]]);var ge=fe;const ve=[{path:"/",name:"subjects",component:V,meta:{requiresAuth:!0}},{path:"/login",name:"login",component:ge},{path:"/register",name:"register",component:se},{path:"/subject/:id",name:"SubjectLayout",props:!0,component:H,children:[{path:"",name:"SubjectDetails",component:F},{path:"register",name:"SubjectQueue",component:K},{path:"assignments",name:"SubjectAssignments",component:re}]}],he=(0,y.p7)({history:(0,y.r5)(),routes:ve});he.beforeEach(((e,t,r)=>{const n=["/login"],o=!n.includes(e.path),a=localStorage.getItem("user");return o&&!a?r("/login"):a&&!o?r("/"):void r()}));var be=he,we=r(6265),_e=r.n(we),je=(0,f.MT)({state:{user:null},getters:{loggedIn(e){return!!e.user}},mutations:{SET_USER_DATA(e,t){e.user=t.data,localStorage.setItem("user",JSON.stringify(t)),_e().defaults.headers.common.authorization="Bearer ${user.token}"},CLEAR_USER_DATA(){localStorage.removeItem("user"),location.reload()}},actions:{login({commit:e},t){return _e().post("http://localhost:8001/user/login",null,{params:{email:t.email,password:t.password}}).then((t=>{console.log(t),e("SET_USER_DATA",t)}))},register({commit:e},t){return _e().post("http://localhost:8001/user/register",t).then((t=>{e("SET_USER_DATA",t)}))},logout({commit:e}){e("CLEAR_USER_DATA")}},modules:{}}),Se=r(4609),ye=r.n(Se),De=r(8420),Te=r.n(De);const ke=r(1917),Ie=(0,n.ri)(S);ke.keys().forEach((e=>{const t=ke(e),r=ye()(Te()(e.replace(/^\.\/(.*)\.\w+$/,"$1")));Ie.component(r,t.default||t)})),Ie.use(je).use(be).mount("#app")},4524:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return l}});var n=r(3396);const o=["id"];function a(e,t,r,a,s,u){return(0,n.wg)(),(0,n.iD)("p",{"aria-live":"assertive",class:"errorMessage",id:r.id},[(0,n.WI)(e.$slots,"default",{},void 0,!0)],8,o)}var s={props:{id:{type:[String,Number],required:!0}}},u=r(89);const i=(0,u.Z)(s,[["render",a],["__scopeId","data-v-b3a6cd26"]]);var l=i},3211:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return f}});var n=r(3396),o=r(7139);const a={class:"container"},s=["for"],u=["id","value","placeholder","aria-describedby","aria-invalid"];function i(e,t,r,i,l,c){const d=(0,n.up)("BaseErrorMessage");return(0,n.wg)(),(0,n.iD)("div",a,[r.label?((0,n.wg)(),(0,n.iD)("label",{key:0,for:i.uuid},(0,o.zw)(r.label),9,s)):(0,n.kq)("",!0),(0,n._)("input",(0,n.dG)({class:"field"},e.$attrs,{onInput:t[0]||(t[0]=t=>e.$emit("update:modelValue",t.target.value)),id:i.uuid,value:r.modelValue,placeholder:r.label,"aria-describedby":r.error?`${i.uuid}-error`:null,"aria-invalid":!!r.error,class:{error:r.error}}),null,16,u),(0,n.Wm)(d,{id:`${i.uuid}-error`},{default:(0,n.w5)((()=>[(0,n.Uk)((0,o.zw)(r.error),1)])),_:1},8,["id"])])}let l=0;function c(){const e=()=>(l++,l);return{getID:e}}var d={props:{label:{type:String,default:""},error:{type:String,default:""},modelValue:{type:[String,Number],default:""}},setup(){const e=c().getID();return{uuid:e}}},m=r(89);const p=(0,m.Z)(d,[["render",i],["__scopeId","data-v-252fad5b"]]);var f=p},1917:function(e,t,r){var n={"./BaseErrorMessage.vue":4524,"./BaseInput.vue":3211};function o(e){var t=a(e);return r(t)}function a(e){if(!r.o(n,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return n[e]}o.keys=function(){return Object.keys(n)},o.resolve=a,e.exports=o,o.id=1917},6949:function(e,t,r){"use strict";e.exports=r.p+"img/logo.d1e80654.png"}},t={};function r(n){var o=t[n];if(void 0!==o)return o.exports;var a=t[n]={id:n,loaded:!1,exports:{}};return e[n](a,a.exports,r),a.loaded=!0,a.exports}r.m=e,function(){var e=[];r.O=function(t,n,o,a){if(!n){var s=1/0;for(c=0;c<e.length;c++){n=e[c][0],o=e[c][1],a=e[c][2];for(var u=!0,i=0;i<n.length;i++)(!1&a||s>=a)&&Object.keys(r.O).every((function(e){return r.O[e](n[i])}))?n.splice(i--,1):(u=!1,a<s&&(s=a));if(u){e.splice(c--,1);var l=o();void 0!==l&&(t=l)}}return t}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[n,o,a]}}(),function(){r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,{a:t}),t}}(),function(){r.d=function(e,t){for(var n in t)r.o(t,n)&&!r.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:t[n]})}}(),function(){r.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){r.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){r.p="/"}(),function(){var e={143:0};r.O.j=function(t){return 0===e[t]};var t=function(t,n){var o,a,s=n[0],u=n[1],i=n[2],l=0;if(s.some((function(t){return 0!==e[t]}))){for(o in u)r.o(u,o)&&(r.m[o]=u[o]);if(i)var c=i(r)}for(t&&t(n);l<s.length;l++)a=s[l],r.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return r.O(c)},n=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}();var n=r.O(void 0,[998],(function(){return r(7827)}));n=r.O(n)})();
//# sourceMappingURL=app.479243d6.js.map