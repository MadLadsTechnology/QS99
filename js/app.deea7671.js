(function(){var e={8764:function(e,t,s){"use strict";var r=s(9242),o=s(3396);function l(e,t,s,r,l,n){const i=(0,o.up)("NavigationMenu"),u=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o.Wm)(i),(0,o.Wm)(u)],64)}var n=s(7139),i=s(6949);const u=e=>((0,o.dD)("data-v-11c2b386"),e=e(),(0,o.Cn)(),e),a={class:"container"},d=u((()=>(0,o._)("img",{src:i,alt:"Logo"},null,-1))),c={key:0},m=u((()=>(0,o._)("h3",null,"Dashboard",-1))),h=u((()=>(0,o._)("h3",null,"Subjects",-1))),b={key:1,class:"userInformation"},p={class:"role"};function w(e,t,s,r,l,i){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("div",a,[(0,o.Wm)(u,{to:"/"},{default:(0,o.w5)((()=>[d])),_:1}),e.loggedIn?((0,o.wg)(),(0,o.iD)("div",c,[this.$store.getters.isAdmin?((0,o.wg)(),(0,o.j4)(u,{key:0,class:"routerLink",to:"/subjects"},{default:(0,o.w5)((()=>[m])),_:1})):((0,o.wg)(),(0,o.j4)(u,{key:1,class:"routerLink",to:"/subjects"},{default:(0,o.w5)((()=>[h])),_:1}))])):(0,o.kq)("",!0),e.loggedIn?((0,o.wg)(),(0,o.iD)("div",b,[(0,o._)("h3",p,(0,n.zw)(this.$store.state.user.role)+":",1),(0,o._)("h3",null,(0,n.zw)(this.$store.state.user.emailAddress),1),(0,o._)("button",{class:"logOutBtn",onClick:t[0]||(t[0]=e=>i.logOut())},"Log out")])):(0,o.kq)("",!0)])}var j=s(65);const g={...(0,j.Se)(["loggedIn"])};var v={methods:{async logOut(){await this.$store.dispatch("logout"),await this.$router.push("/login")}},computed:{...g}},_=s(89);const f=(0,_.Z)(v,[["render",w],["__scopeId","data-v-11c2b386"]]);var y=f,S={created(){const e=localStorage.getItem("user");if(e){const t=JSON.parse(e);this.$store.commit("SET_USER_DATA",t)}document.title="QS99"},components:{NavigationMenu:y},computed:{}};const A=(0,_.Z)(S,[["render",l]]);var k=A,U=s(678);const D=e=>((0,o.dD)("data-v-15b8a499"),e=e(),(0,o.Cn)(),e),W={key:0,class:"teacherToolbar"},C={key:1},x=D((()=>(0,o._)("h3",null,"Active queues",-1))),V={class:"cardHolder"},q={key:2},I=D((()=>(0,o._)("h3",null,"Student Assistant subjects",-1))),M={class:"cardHolder"},z={key:3},$=D((()=>(0,o._)("h3",null,"Subjects",-1))),E={class:"cardHolder"};function T(e,t,s,r,l,n){const i=(0,o.up)("SubjectCard");return(0,o.wg)(),(0,o.iD)(o.HY,null,[this.$store.getters.isProfessor?((0,o.wg)(),(0,o.iD)("div",W,[(0,o._)("button",{onClick:t[0]||(t[0]=e=>this.$router.push("/createSubject"))}," Create new subject ")])):(0,o.kq)("",!0),n.subjects.active.length>0?((0,o.wg)(),(0,o.iD)("div",C,[x,(0,o._)("div",V,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(n.subjects.active,(e=>((0,o.wg)(),(0,o.j4)(i,{key:parseInt(e.id),subject:e},null,8,["subject"])))),128))])])):(0,o.kq)("",!0),n.subjects.inStudAss.length>0?((0,o.wg)(),(0,o.iD)("div",q,[I,(0,o._)("div",M,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(n.subjects.inStudAss,(e=>((0,o.wg)(),(0,o.j4)(i,{key:parseInt(e.id),subject:e},null,8,["subject"])))),128))])])):(0,o.kq)("",!0),n.subjects.inActive.length>0?((0,o.wg)(),(0,o.iD)("div",z,[$,(0,o._)("div",E,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(n.subjects.inActive,(e=>((0,o.wg)(),(0,o.j4)(i,{key:parseInt(e.id),subject:e},null,8,["subject"])))),128))])])):(0,o.kq)("",!0)],64)}const N=e=>((0,o.dD)("data-v-4698eb7a"),e=e(),(0,o.Cn)(),e),Z={class:"card"},O=N((()=>(0,o._)("br",null,null,-1)));function H(e,t,s,r,l,i){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.j4)(u,{to:{name:i.link,params:{id:s.subject.id}}},{default:(0,o.w5)((()=>[(0,o._)("div",Z,[(0,o._)("h1",null,(0,n.zw)(s.subject.subjectCode),1),(0,o.Uk)(" "+(0,n.zw)(s.subject.subjectName)+" "+(0,n.zw)(s.subject.id)+" ",1),O])])),_:1},8,["to"])}var Q={props:{subject:{type:Object,required:!0}},computed:{link:function(){return this.subject.queueActive?"SubjectQueue":"SubjectAssignments"}}};const R=(0,_.Z)(Q,[["render",H],["__scopeId","data-v-4698eb7a"]]);var Y=R,L=s(6265),B=s.n(L),P={name:"HomeView",components:{SubjectCard:Y},async created(){document.title="QS99 - Subjects",await B().get("http://localhost:8001/subject/getByUser").then((e=>{this.allSubjects=e.data}))},data(){return{allSubjects:[]}},computed:{...g,subjects:function(){const e=[],t=[],s=[];for(let r=0;r<this.allSubjects.length;r++)!0===this.allSubjects[r].isStudAss?s.push(this.allSubjects[r]):!0===this.allSubjects[r].queueActive&&!1===this.allSubjects[r].isStudAss?e.push(this.allSubjects[r]):t.push(this.allSubjects[r]);return{active:e,inActive:t,inStudAss:s}}}};const F=(0,_.Z)(P,[["render",T],["__scopeId","data-v-15b8a499"]]);var K=F;const G=e=>((0,o.dD)("data-v-13f86774"),e=e(),(0,o.Cn)(),e),J={key:1,class:"container"},X={key:0},ee={class:"nav"},te=G((()=>(0,o._)("div",null,"Queue",-1))),se=G((()=>(0,o._)("div",null,"Details",-1))),re=G((()=>(0,o._)("div",null,"Assignments",-1))),oe=G((()=>(0,o._)("div",null,"Users",-1)));function le(e,t,s,r,l,i){const u=(0,o.up)("ProfessorActions"),a=(0,o.up)("router-link"),d=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[this.$store.getters.isStudent?(0,o.kq)("",!0):((0,o.wg)(),(0,o.j4)(u,{key:0,subject:l.subject},null,8,["subject"])),l.subject?((0,o.wg)(),(0,o.iD)("div",J,[(0,o._)("h1",null,(0,n.zw)(l.subject.subjectCode),1),l.subject.isStudAss?((0,o.wg)(),(0,o.iD)("h4",X," Hello, you are a student assistant in this subject ")):(0,o.kq)("",!0),(0,o._)("div",ee,[(0,o.Wm)(a,{to:{name:"SubjectQueue"}},{default:(0,o.w5)((()=>[te])),_:1}),(0,o.Wm)(a,{to:{name:"SubjectDetails"}},{default:(0,o.w5)((()=>[se])),_:1}),this.$store.getters.isStudent?((0,o.wg)(),(0,o.j4)(a,{key:0,to:{name:"SubjectAssignments"}},{default:(0,o.w5)((()=>[re])),_:1})):(0,o.kq)("",!0),this.$store.getters.isStudent?(0,o.kq)("",!0):((0,o.wg)(),(0,o.j4)(a,{key:1,to:{name:"subjectUsers"}},{default:(0,o.w5)((()=>[oe])),_:1}))]),(0,o.Wm)(d,{subject:l.subject},null,8,["subject"])])):(0,o.kq)("",!0)],64)}function ne(e,t,s,r,l,n){const i=(0,o.up)("AddExercises"),u=(0,o.up)("AddUserToSubject"),a=(0,o.up)("AddMultipleUsersToSubject");return(0,o.wg)(),(0,o.iD)(o.HY,null,[l.showAddExercises?((0,o.wg)(),(0,o.j4)(i,{key:0,onCloseWindow:n.closeWindow,subject:l.currentSubject},null,8,["onCloseWindow","subject"])):(0,o.kq)("",!0),l.showAddSingleUser?((0,o.wg)(),(0,o.j4)(u,{key:1,onCloseWindow:n.closeWindow,subject:l.currentSubject},null,8,["onCloseWindow","subject"])):(0,o.kq)("",!0),l.showAddMultipleUsers?((0,o.wg)(),(0,o.j4)(a,{key:2,onCloseWindowMultipleUsers:n.closeWindow,subject:l.currentSubject},null,8,["onCloseWindowMultipleUsers","subject"])):(0,o.kq)("",!0),(0,o._)("button",{onClick:t[0]||(t[0]=t=>n.showSingleUserWindow(e.id,s.subject.subjectCode))}," Add user "),(0,o._)("button",{onClick:t[1]||(t[1]=t=>n.showMultipleUserWindow(e.id,s.subject.subjectCode))}," Add multiple users "),(0,o._)("button",{onClick:t[2]||(t[2]=t=>n.showAddExercisesWindow(e.id,s.subject.subjectCode))}," Add exercises ")],64)}const ie={class:"window"},ue=["disabled"];function ae(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",ie,[(0,o._)("button",{onClick:t[0]||(t[0]=e=>u.closeWindow())},"Close"),(0,o._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,r.iM)((e=>u.submit()),["prevent"]))},[(0,o._)("h1",null,"Add user to "+(0,n.zw)(s.subject.code),1),(0,o.Wm)(a,{label:"Email",type:"email",modelValue:l.email,"onUpdate:modelValue":t[1]||(t[1]=e=>l.email=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,ue)],32)])}var de=s(5708),ce=s(6542),me={props:{subject:{type:Object,required:!0}},data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){B().post("http://localhost:8001/subject/addUser",null,{params:{subjectId:this.subject.id,email:this.email}}).then((()=>{this.closeWindow()})).catch((e=>{alert(e)}))},closeWindow(){this.$emit("closeWindow")}},setup(){const e=(0,ce.Ry)({email:(0,ce.Z_)().email("Invalid email format").required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("email");return{email:s,errors:t}},computed:{isValid(){return!this.errors.email&&this.email}}};const he=(0,_.Z)(me,[["render",ae]]);var be=he;const pe={class:"background"},we={class:"window"},je=["disabled"];function ge(e,t,s,l,i,u){return(0,o.wg)(),(0,o.iD)("div",pe,[(0,o._)("div",we,[(0,o._)("button",{onClick:t[0]||(t[0]=e=>u.closeWindow())},"Close"),(0,o._)("h1",null,"Add multiple users to "+(0,n.zw)(s.subject.code),1),(0,o.wy)((0,o._)("textarea",{"onUpdate:modelValue":t[1]||(t[1]=e=>i.users=e),placeholder:"Paste emails separated by new lines"},"\n      ",512),[[r.nr,i.users]]),(0,o._)("button",{disabled:null==i.users,onClick:t[2]||(t[2]=(...e)=>u.submit&&u.submit(...e))},"Submit",8,je)])])}var ve={props:{subject:{type:Object,required:!0}},data(){return{error:null,users:""}},created(){document.title="QS99 - Login"},methods:{submit(){B().post("http://localhost:8001/subject/addUsers",{data:this.users},{params:{subjectId:this.subject.id}}).then((()=>{this.closeWindow()})).catch((e=>{alert(e)}))},closeWindow(){this.$emit("closeWindowMultipleUsers")}},computed:{}};const _e=(0,_.Z)(ve,[["render",ge]]);var fe=_e;const ye={class:"window"},Se=["disabled"];function Ae(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",ye,[(0,o._)("button",{onClick:t[0]||(t[0]=e=>u.closeWindow())},"Close"),(0,o._)("form",{class:"loginForm",onSubmit:t[3]||(t[3]=(0,r.iM)((e=>u.submit()),["prevent"]))},[(0,o._)("h1",null,"Add exercises to "+(0,n.zw)(s.subject.code),1),(0,o.Wm)(a,{label:"Number of exercises",type:"number",modelValue:l.count,"onUpdate:modelValue":t[1]||(t[1]=e=>l.count=e),modelModifiers:{lazy:!0},error:l.errors.count},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Number of mandatory",type:"number",modelValue:l.mandatory,"onUpdate:modelValue":t[2]||(t[2]=e=>l.mandatory=e),modelModifiers:{lazy:!0},error:l.errors.mandatory},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,Se)],32)])}var ke={props:{subject:{type:Object,required:!0}},data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){B().post("http://localhost:8001/exercise",null,{params:{subjectId:this.subject.id,numberOfExercises:this.count,numberOfMandatory:this.mandatory}}).then((()=>{this.closeWindow()})).catch((e=>{alert(e)}))},closeWindow(){this.$emit("closeWindow")}},setup(){const e=(0,ce.Ry)({count:(0,ce.Rx)().min(1).required(),mandatory:(0,ce.Rx)().min(1).required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("count"),{value:r}=(0,de.U$)("mandatory");return{count:s,mandatory:r,errors:t}},computed:{isValid(){return!this.errors.count&&!this.errors.mandatory&&(this.count&&this.mandatory)}}};const Ue=(0,_.Z)(ke,[["render",Ae]]);var De=Ue,We={components:{AddUserToSubject:be,AddMultipleUsersToSubject:fe,AddExercises:De},props:{subject:{type:Object,required:!0}},methods:{setCurrentSubject(e,t){this.currentSubject={code:t,id:e}},showSingleUserWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!0,this.showAddMultipleUsers=!1},showMultipleUserWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!1,this.showAddMultipleUsers=!0},showAddExercisesWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!1,this.showAddMultipleUsers=!1,this.showAddExercises=!0},closeWindow(){this.showAddSingleUser=!1,this.showAddMultipleUsers=!1,this.showAddExercises=!1,location.reload()},async removeUser(e){await B()["delete"]("http://localhost:8001/subject/deleteUserFromSubject",{params:{subjectId:this.id,emailAddress:e.emailAddress}}).then((e=>{e&&location.reload()}))},async removeExercise(e){await B()["delete"]("http://localhost:8001/exercise",{params:{subjectId:this.id,exerciseNumber:e.exerciseNumber}}).then((()=>{location.reload()}))}},async created(){await B().get("http://localhost:8001/user/getAllUsersFromSubject",{params:{subjectId:this.id}}).then((e=>{this.users=e.data,console.table(this.users)}))},data(){return{users:[],subjects:null,showAddSingleUser:!1,showAddMultipleUsers:!1,showAddExercises:!1,currentSubject:null,assignments:[]}}};const Ce=(0,_.Z)(We,[["render",ne]]);var xe=Ce,Ve={components:{ProfessorActions:xe},props:["id"],data(){return{subject:null}},async created(){await B().get("http://localhost:8001/subject/getSubject",{params:{subjectId:parseInt(this.id)}}).then((e=>{this.subject=e.data})).catch((e=>{alert(e)})),document.title="QS99 - "+this.subject.subjectCode}};const qe=(0,_.Z)(Ve,[["render",le],["__scopeId","data-v-13f86774"]]);var Ie=qe;function Me(e,t,s,r,l,i){return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o._)("h1",null,(0,n.zw)(s.subject.subjectName),1),(0,o._)("h3",null,(0,n.zw)(s.subject.subjectDescription),1)],64)}var ze={props:["subject"]};const $e=(0,_.Z)(ze,[["render",Me]]);var Ee=$e;const Te=["disabled"],Ne=(0,o._)("th",null,"Place",-1),Ze=(0,o._)("th",null,"Last name",-1),Oe=(0,o._)("th",null,"First name",-1),He=(0,o._)("th",null,"Assignment",-1),Qe=(0,o._)("th",null,"Type",-1),Re=(0,o._)("th",null,"Table",-1),Ye={key:0};function Le(e,t,s,r,l,i){return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o._)("button",{disabled:l.inQueue,onClick:t[0]||(t[0]=e=>this.$router.push("JoinQueue"))}," Join queue ",8,Te),(0,o._)("table",null,[(0,o._)("tr",null,[Ne,Ze,Oe,He,Qe,Re,this.$store.getters.isStudent?(0,o.kq)("",!0):((0,o.wg)(),(0,o.iD)("th",Ye,"Actions"))]),((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(l.queue,((e,t)=>((0,o.wg)(),(0,o.iD)("tr",{key:e.lastname},[(0,o._)("td",null,(0,n.zw)(t),1),(0,o._)("td",null,(0,n.zw)(e.lastName),1),(0,o._)("td",null,(0,n.zw)(e.firstName),1),(0,o._)("td",null,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(e.exercises,(e=>((0,o.wg)(),(0,o.iD)("text",{key:e},(0,n.zw)(e)+", ",1)))),128))]),(0,o._)("td",null,(0,n.zw)(e.type),1),(0,o._)("td",null,(0,n.zw)(e.tableNumber),1)])))),128))])],64)}var Be={props:["subject"],created(){B().get("http://localhost:8001/queue",{params:{subjectId:this.subject.id}}).then((e=>{console.log(e.data),this.queue=e.data,this.queue.some((e=>{e.studentId===this.$store.state.user.emailAddress&&(this.inQueue=!0)}))}))},methods:{setInQueue(){}},data(){return{queue:null,inQueue:!1}}};const Pe=(0,_.Z)(Be,[["render",Le]]);var Fe=Pe;const Ke={key:0},Ge={key:1},Je=(0,o._)("tr",null,[(0,o._)("th",null,"Number"),(0,o._)("th",null,"Approved")],-1);function Xe(e,t,s,r,l,i){return i.hasPrivileges?((0,o.wg)(),(0,o.iD)("div",Ke)):((0,o.wg)(),(0,o.iD)("div",Ge,[(0,o._)("table",null,[Je,((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(l.assignments,(e=>((0,o.wg)(),(0,o.iD)("tr",{key:parseInt(e.id)},[(0,o._)("td",null,(0,n.zw)(e.exerciseNumber),1),(0,o._)("td",null,(0,n.zw)(e.isApproved),1)])))),128))])]))}var et={name:"AssignementView",props:["subject"],components:{},methods:{},async created(){document.title="QS99 - Subjects",this.hasPrivileges&&await B().get("http://localhost:8001/user/getAllUsersFromSubject",{params:{subjectId:parseInt(this.subject.id)}}).then((e=>{this.students=e.data})),await B().get("http://localhost:8001/exercise/getByUser",{params:{subjectId:parseInt(this.subject.id)}}).then((e=>{e.data.length>0&&(this.assignments=e.data)}))},data(){return{assignments:null,students:null}},computed:{hasPrivileges:function(){return!!(this.subject.isStudAss||this.$store.getters.isProfessor||this.$store.getters.isAdmin)}}};const tt=(0,_.Z)(et,[["render",Xe]]);var st=tt;const rt=(0,o._)("tr",null,[(0,o._)("th",null,"Last name"),(0,o._)("th",null,"First name"),(0,o._)("th",null,"Email"),(0,o._)("th",null,"Role"),(0,o._)("th",null,"Exercises"),(0,o._)("th",null,"Actions")],-1),ot={class:"exerciseWrapper"},lt=["onClick"];function nt(e,t,s,r,l,i){const u=(0,o.up)("ExerciseBox");return(0,o.wg)(),(0,o.iD)("table",null,[rt,((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(l.users,(e=>((0,o.wg)(),(0,o.iD)("tr",{key:e.emailAddress},[(0,o._)("td",null,(0,n.zw)(e.lastName),1),(0,o._)("td",null,(0,n.zw)(e.firstName),1),(0,o._)("td",null,(0,n.zw)(e.emailAddress),1),(0,o._)("td",null,(0,n.zw)(e.role),1),(0,o._)("td",null,[(0,o._)("div",ot,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(e.exercises,(e=>((0,o.wg)(),(0,o.j4)(u,{key:e,exercise:e},null,8,["exercise"])))),128))])]),(0,o._)("td",null,[(0,o._)("button",{onClick:t=>i.removeUser(e)},"Remove",8,lt)])])))),128))])}function it(e,t,s,r,l,i){return(0,o.wg)(),(0,o.iD)("div",{class:"box",style:(0,n.j5)({backgroundColor:this.color})},(0,n.zw)(this.exercise.exerciseNumber),5)}var ut={props:{exercise:{type:Object,required:!0}},computed:{color:function(){return!0===this.exercise.approved?"green":"red"}}};const at=(0,_.Z)(ut,[["render",it],["__scopeId","data-v-2f0fcc81"]]);var dt=at,ct={name:"SubjectUsersView",props:["subject"],components:{ExerciseBox:dt},data(){return{users:[]}},methods:{async removeUser(e){await B()["delete"]("http://localhost:8001/subject/deleteUserFromSubject",{params:{subjectId:this.id,emailAddress:e.emailAddress}}).then((e=>{e&&location.reload()}))},async removeExercise(e){await B()["delete"]("http://localhost:8001/exercise",{params:{subjectId:this.id,exerciseNumber:e.exerciseNumber}}).then((()=>{location.reload()}))}},async created(){await B().get("http://localhost:8001/user/getAllUsersFromSubject",{params:{subjectId:parseInt(this.subject.id)}}).then((e=>{this.users=e.data,console.table(this.users)}))}};const mt=(0,_.Z)(ct,[["render",nt]]);var ht=mt;const bt=(0,o._)("h1",null,"Get in the queue!",-1),pt={class:"checkBoxHolder"},wt=["id","disabled","value"],jt=["for"],gt=(0,o._)("label",{for:"one"},"Help",-1),vt=(0,o._)("br",null,null,-1),_t=(0,o._)("label",{for:"two"},"Approval",-1),ft=(0,o._)("br",null,null,-1),yt=["disabled"],St={key:0};function At(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",null,[i.assignments?((0,o.wg)(),(0,o.iD)("form",{key:0,class:"loginForm",onSubmit:t[6]||(t[6]=(0,r.iM)((e=>u.submit()),["prevent"]))},[bt,(0,o.Wm)(a,{label:"Room",type:"text",modelValue:l.room,"onUpdate:modelValue":t[0]||(t[0]=e=>l.room=e),modelModifiers:{lazy:!0},error:l.errors.room},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Building",type:"text",modelValue:l.building,"onUpdate:modelValue":t[1]||(t[1]=e=>l.building=e),modelModifiers:{lazy:!0},error:l.errors.building},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Table number",type:"number",modelValue:l.tableNumber,"onUpdate:modelValue":t[2]||(t[2]=e=>l.tableNumber=e),modelModifiers:{lazy:!0},error:l.errors.tableNumber},null,8,["modelValue","error"]),(0,o._)("div",pt,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(i.assignments,(e=>((0,o.wg)(),(0,o.iD)("div",{key:e},[(0,o.wy)((0,o._)("input",{type:"checkbox",id:e.id,disabled:e.approved,value:e.id,"onUpdate:modelValue":t[3]||(t[3]=e=>i.exercises=e)},null,8,wt),[[r.e8,i.exercises]]),(0,o._)("label",{for:e.id},(0,n.zw)(e.exerciseNumber),9,jt)])))),128))]),(0,o.wy)((0,o._)("input",{type:"radio",id:"one",value:"help",checked:"checked","onUpdate:modelValue":t[4]||(t[4]=e=>i.helpType=e)},null,512),[[r.G2,i.helpType]]),gt,vt,(0,o.wy)((0,o._)("input",{type:"radio",id:"two",value:"approval","onUpdate:modelValue":t[5]||(t[5]=e=>i.helpType=e)},null,512),[[r.G2,i.helpType]]),_t,ft,(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,yt),i.error?((0,o.wg)(),(0,o.iD)("p",St,(0,n.zw)(i.error),1)):(0,o.kq)("",!0)],32)):(0,o.kq)("",!0)])}var kt={props:["subject"],data(){return{error:null,helpType:"help",assignments:null,exercises:[]}},created(){B().get("http://localhost:8001/exercise/getByUser",{params:{subjectId:parseInt(this.subject.id)}}).then((e=>{this.assignments=e.data})).catch((e=>{console.log(e)})),document.title="QS99 - Join queue"},methods:{submit(){console.log(this.exercises),B().post("http://localhost:8001/queue/addEntry",{exercises:this.exercises},{params:{room:this.room,building:this.building,tableNumber:this.tableNumber,type:this.helpType,subjectId:this.subject.id}}).then((()=>{this.$router.push("/subjects/:id/queue")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,ce.Ry)({room:(0,ce.Z_)().required(),building:(0,ce.Z_)().required(),tableNumber:(0,ce.Rx)().required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("room"),{value:r}=(0,de.U$)("building"),{value:o}=(0,de.U$)("tableNumber");return{room:s,building:r,tableNumber:o,errors:t}},computed:{isValid(){return!(this.errors.room||this.errors.building||this.errors.tableNumber)&&(this.room&&this.building&&this.tableNumber)}}};const Ut=(0,_.Z)(kt,[["render",At]]);var Dt=Ut;const Wt=(0,o._)("h1",null,"Register a new user",-1),Ct={class:"nameInputs"},xt={key:0,class:"radioButtons"},Vt=(0,o._)("label",{for:"professor"},"Professor",-1),qt=(0,o._)("label",{for:"student"},"Student",-1),It=["disabled"],Mt={key:1};function zt(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("form",{class:"loginForm",onSubmit:t[5]||(t[5]=(0,r.iM)((e=>u.submit()),["prevent"]))},[Wt,(0,o._)("div",Ct,[(0,o.Wm)(a,{label:"Last name",type:"lastname",modelValue:l.lastname,"onUpdate:modelValue":t[0]||(t[0]=e=>l.lastname=e),modelModifiers:{lazy:!0},error:l.errors.lastname},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"First name",type:"text",modelValue:l.firstname,"onUpdate:modelValue":t[1]||(t[1]=e=>l.firstname=e),modelModifiers:{lazy:!0},error:l.errors.firstname},null,8,["modelValue","error"])]),(0,o.Wm)(a,{label:"Email",type:"email",modelValue:l.email,"onUpdate:modelValue":t[2]||(t[2]=e=>l.email=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),this.$store.getters.isAdmin?((0,o.wg)(),(0,o.iD)("div",xt,[(0,o._)("div",null,[(0,o.wy)((0,o._)("input",{type:"radio",id:"professor",value:"Professor","onUpdate:modelValue":t[3]||(t[3]=e=>this.userType=e)},null,512),[[r.G2,this.userType]]),Vt]),(0,o._)("div",null,[(0,o.wy)((0,o._)("input",{type:"radio",id:"student",value:"Student","onUpdate:modelValue":t[4]||(t[4]=e=>this.userType=e)},null,512),[[r.G2,this.userType]]),qt])])):(0,o.kq)("",!0),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,It),i.error?((0,o.wg)(),(0,o.iD)("p",Mt,(0,n.zw)(i.error),1)):(0,o.kq)("",!0)],32)])}var $t={data(){return{error:null,userType:""}},created(){document.title="QS99 - Login"},methods:{submit(){let e="http://localhost:8001/user/registerStudent";console.log(this.userType),"Professor"===this.userType&&(e="http://localhost:8001/user/registerProfessor"),B().post(e,null,{params:{lastname:this.lastname,firstname:this.firstname,email:this.email}}).then((()=>{this.$router.push("/admin/users")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,ce.Ry)({lastname:(0,ce.Z_)().required(),firstname:(0,ce.Z_)().required(),email:(0,ce.Z_)().email("Invalid email format").required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("lastname"),{value:r}=(0,de.U$)("firstname"),{value:o}=(0,de.U$)("email");return{lastname:s,firstname:r,email:o,errors:t}},computed:{isValid(){return!(this.errors.email||this.errors.lastname||this.errors.firstname)&&(this.email&&this.lastname&&this.firstname)}}};const Et=(0,_.Z)($t,[["render",zt]]);var Tt=Et;const Nt=(0,o._)("h1",null,"Log in",-1),Zt=["disabled"],Ot=(0,o._)("br",null,null,-1),Ht={key:0};function Qt(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,r.iM)((e=>u.submit()),["prevent"]))},[Nt,(0,o.Wm)(a,{label:"Email",type:"email",modelValue:l.email,"onUpdate:modelValue":t[0]||(t[0]=e=>l.email=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Password",type:"password",modelValue:l.password,"onUpdate:modelValue":t[1]||(t[1]=e=>l.password=e),error:l.errors.password},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Log in",8,Zt),Ot,i.error?((0,o.wg)(),(0,o.iD)("p",Ht,(0,n.zw)(l.errors),1)):(0,o.kq)("",!0)],32)])}var Rt={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{async submit(){await this.$store.dispatch("login",{email:this.email,password:this.password}).then((()=>{this.$router.push("/subjects")})).catch((e=>{this.error=e}))}},setup(){const e=(0,ce.Ry)({email:(0,ce.Z_)().email("Invalid email format").required(),password:(0,ce.Z_)().required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("email"),{value:r}=(0,de.U$)("password");return{email:s,password:r,errors:t}},computed:{isValid(){return!this.errors.email&&!this.errors.password&&(this.email&&this.password)}}};const Yt=(0,_.Z)(Rt,[["render",Qt]]);var Lt=Yt;const Bt={id:"nav"},Pt=(0,o.Uk)("Users"),Ft=(0,o.Uk)(" | "),Kt=(0,o.Uk)("Subjects");function Gt(e,t){const s=(0,o.up)("router-link"),r=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o._)("div",Bt,[(0,o.Wm)(s,{to:{name:"users"}},{default:(0,o.w5)((()=>[Pt])),_:1}),Ft,(0,o.Wm)(s,{to:{name:"allSubjects"}},{default:(0,o.w5)((()=>[Kt])),_:1})]),(0,o.Wm)(r)],64)}const Jt={},Xt=(0,_.Z)(Jt,[["render",Gt]]);var es=Xt;const ts=e=>((0,o.dD)("data-v-83eb8cf0"),e=e(),(0,o.Cn)(),e),ss=ts((()=>(0,o._)("h3",null,"All users",-1))),rs=ts((()=>(0,o._)("button",null,"Register user",-1))),os=ts((()=>(0,o._)("tr",null,[(0,o._)("th",null,"Email"),(0,o._)("th",null,"Last name"),(0,o._)("th",null,"First name"),(0,o._)("th",null,"Role"),(0,o._)("th",null,"Actions")],-1))),ls=["onClick"];function ns(e,t,s,r,l,i){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[ss,(0,o.Wm)(u,{to:"/register"},{default:(0,o.w5)((()=>[rs])),_:1}),(0,o._)("table",null,[os,((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(l.users,(e=>((0,o.wg)(),(0,o.iD)("tr",{key:e},[(0,o._)("td",null,(0,n.zw)(e.emailAddress),1),(0,o._)("td",null,(0,n.zw)(e.lastName),1),(0,o._)("td",null,(0,n.zw)(e.firstName),1),(0,o._)("td",null,(0,n.zw)(e.role),1),(0,o._)("td",null,[e.emailAddress!==this.$store.state.user.emailAddress?((0,o.wg)(),(0,o.iD)("button",{key:0,onClick:t=>i.deleteUser(e)}," Delete ",8,ls)):(0,o.kq)("",!0)])])))),128))])],64)}var is={name:"HomeView",methods:{deleteUser(e){B()["delete"]("http://localhost:8001/user",{params:{email:e.emailAddress}}).then((e=>{this.users=e.data})).catch((e=>{alert(e.message)}))}},components:{},created(){document.title="QS99 - Students",B().get("http://localhost:8001/user/getAllUsers").then((e=>{this.users=e.data}))},data(){return{users:null}},computed:{...g}};const us=(0,_.Z)(is,[["render",ns],["__scopeId","data-v-83eb8cf0"]]);var as=us;const ds=(0,o._)("h3",null,"All Subjects",-1),cs=(0,o._)("button",null,"Create a subject",-1),ms=(0,o._)("tr",null,[(0,o._)("th",null,"Code"),(0,o._)("th",null,"Name name"),(0,o._)("th",null,"Description"),(0,o._)("th",null,"Year"),(0,o._)("th",null,"Actions")],-1),hs=(0,o._)("button",null,"Info",-1),bs=["onClick"],ps=["onClick"],ws=["onClick"],js=["onClick"],gs=["onClick"];function vs(e,t,s,r,l,i){const u=(0,o.up)("AddExercises"),a=(0,o.up)("AddUserToSubject"),d=(0,o.up)("AddMultipleUsersToSubject"),c=(0,o.up)("AddAssistantToSubject"),m=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[l.showAddExercises?((0,o.wg)(),(0,o.j4)(u,{key:0,onCloseWindow:i.closeWindow,subject:l.currentSubject},null,8,["onCloseWindow","subject"])):(0,o.kq)("",!0),l.showAddSingleUser?((0,o.wg)(),(0,o.j4)(a,{key:1,onCloseWindow:i.closeWindow,subject:l.currentSubject},null,8,["onCloseWindow","subject"])):(0,o.kq)("",!0),l.showAddMultipleUsers?((0,o.wg)(),(0,o.j4)(d,{key:2,onCloseWindowMultipleUsers:i.closeWindow,subject:l.currentSubject},null,8,["onCloseWindowMultipleUsers","subject"])):(0,o.kq)("",!0),l.showAddAssistant?((0,o.wg)(),(0,o.j4)(c,{key:3,onCloseWindow:i.closeWindow,subject:l.currentSubject},null,8,["onCloseWindow","subject"])):(0,o.kq)("",!0),ds,(0,o.Wm)(m,{to:"/createSubject"},{default:(0,o.w5)((()=>[cs])),_:1}),(0,o._)("table",null,[ms,((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(l.subjects,(e=>((0,o.wg)(),(0,o.iD)("tr",{key:e.id},[(0,o._)("td",null,(0,n.zw)(e.subjectCode),1),(0,o._)("td",null,(0,n.zw)(e.subjectName),1),(0,o._)("td",null,(0,n.zw)(e.subjectDescription),1),(0,o._)("td",null,(0,n.zw)(e.subjectYear),1),(0,o._)("td",null,[(0,o.Wm)(m,{to:{name:"SubjectLayout",params:{id:e.id}}},{default:(0,o.w5)((()=>[hs])),_:2},1032,["to"]),(0,o._)("button",{onClick:t=>i.showSingleUserWindow(e.id,e.subjectCode)}," Add student ",8,bs),(0,o._)("button",{onClick:t=>i.showMultipleUserWindow(e.id,e.subjectCode)}," Add multiple students ",8,ps),(0,o._)("button",{onClick:t=>i.showSingleAssistantWindow(e.id,e.subjectCode)}," Add student assistant ",8,ws),(0,o._)("button",{onClick:t=>i.showAddExercisesWindow(e.id,e.subjectCode)}," Add exercises ",8,js),(0,o._)("button",{onClick:t=>i.deleteSubject(e)}," Delete Subject ",8,gs)])])))),128))])],64)}const _s={class:"window"},fs=["disabled"];function ys(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",_s,[(0,o._)("button",{onClick:t[0]||(t[0]=e=>u.closeWindow())},"Close"),(0,o._)("form",{class:"loginForm",onSubmit:t[2]||(t[2]=(0,r.iM)((e=>u.submit()),["prevent"]))},[(0,o._)("h1",null,"Add student assistant to "+(0,n.zw)(s.subject.code),1),(0,o.Wm)(a,{label:"Email",type:"email",modelValue:l.email,"onUpdate:modelValue":t[1]||(t[1]=e=>l.email=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,fs)],32)])}var Ss={props:{subject:{type:Object,required:!0}},data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){B().post("http://localhost:8001/subject/addStudentAssistant",null,{params:{subjectId:this.subject.id,email:this.email}}).then((()=>{this.closeWindow()})).catch((e=>{alert(e)}))},closeWindow(){this.$emit("closeWindow")}},setup(){const e=(0,ce.Ry)({email:(0,ce.Z_)().email("Invalid email format").required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("email");return{email:s,errors:t}},computed:{isValid(){return!this.errors.email&&this.email}}};const As=(0,_.Z)(Ss,[["render",ys]]);var ks=As,Us={name:"HomeView",components:{AddUserToSubject:be,AddMultipleUsersToSubject:fe,AddExercises:De,AddAssistantToSubject:ks},methods:{setCurrentSubject(e,t){this.currentSubject={code:t,id:e}},showSingleUserWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!0,this.showAddMultipleUsers=!1,this.showAddExercises=!1,this.showAddAssistant=!1},showMultipleUserWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!1,this.showAddMultipleUsers=!0,this.showAddExercises=!1,this.showAddAssistant=!1},showAddExercisesWindow(e,t){this.setCurrentSubject(e,t),this.showAddSingleUser=!1,this.showAddMultipleUsers=!1,this.showAddExercises=!0,this.showAddAssistant=!1},showSingleAssistantWindow(e,t){this.setCurrentSubject(e,t),this.showAddAssistant=!0,this.showAddSingleUser=!1,this.showAddMultipleUsers=!1,this.showAddExercises=!1},closeWindow(){this.showAddSingleUser=!1,this.showAddMultipleUsers=!1,this.showAddExercises=!1,this.showAddAssistant=!1},deleteSubject(e){let t=confirm("Are you sure to execute this action?");t&&(B()["delete"]("http://localhost:8001/subject",{params:{subjectId:e.id}}),location.reload())}},async created(){document.title="QS99 - Students",await B().get("http://localhost:8001/subject/getAllSubject").then((e=>{this.subjects=e.data}))},data(){return{subjects:null,showAddSingleUser:!1,showAddMultipleUsers:!1,showAddAssistant:!1,showAddExercises:!1,currentSubject:null}},computed:{...g}};const Ds=(0,_.Z)(Us,[["render",vs]]);var Ws=Ds;const Cs=(0,o._)("h1",null,"Create a subject",-1),xs=["disabled"],Vs={key:0};function qs(e,t,s,l,i,u){const a=(0,o.up)("BaseInput");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("form",{class:"loginForm",onSubmit:t[4]||(t[4]=(0,r.iM)((e=>u.submit()),["prevent"]))},[Cs,(0,o.Wm)(a,{label:"Subject code",type:"text",modelValue:l.code,"onUpdate:modelValue":t[0]||(t[0]=e=>l.code=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Subject name",type:"text",modelValue:l.name,"onUpdate:modelValue":t[1]||(t[1]=e=>l.name=e),modelModifiers:{lazy:!0},error:l.errors.firstname},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Description",type:"text",modelValue:l.description,"onUpdate:modelValue":t[2]||(t[2]=e=>l.description=e),modelModifiers:{lazy:!0},error:l.errors.email},null,8,["modelValue","error"]),(0,o.Wm)(a,{label:"Year of lecturing",type:"text",modelValue:l.year,"onUpdate:modelValue":t[3]||(t[3]=e=>l.year=e),error:l.errors.password},null,8,["modelValue","error"]),(0,o._)("button",{disabled:!u.isValid,type:"submit"},"Submit",8,xs),i.error?((0,o.wg)(),(0,o.iD)("p",Vs,(0,n.zw)(i.error),1)):(0,o.kq)("",!0)],32)])}var Is={data(){return{error:null}},created(){document.title="QS99 - Login"},methods:{submit(){B().post("http://localhost:8001/subject/create",null,{params:{subjectName:this.name,subjectCode:this.code,subjectDescription:this.description,year:this.year}}).then((()=>{this.$router.push("/admin/subjects")})).catch((e=>{console.log(e)}))}},setup(){const e=(0,ce.Ry)({name:(0,ce.Z_)().required(),code:(0,ce.Z_)().required(),description:(0,ce.Z_)().required(),year:(0,ce.Z_)().required()}),{errors:t}=(0,de.cI)({validationSchema:e}),{value:s}=(0,de.U$)("name"),{value:r}=(0,de.U$)("code"),{value:o}=(0,de.U$)("description"),{value:l}=(0,de.U$)("year");return{name:s,code:r,description:o,year:l,errors:t}},computed:{isValid(){return!(this.errors.name||this.errors.code||this.errors.description||this.errors.year)&&(this.name&&this.code&&this.description&&this.year)}}};const Ms=(0,_.Z)(Is,[["render",qs]]);var zs=Ms;const $s=(0,j.MT)({state:{user:null},getters:{loggedIn(e){return!!e.user},isProfessor(e){return"Professor"===e.user.role},isAdmin(e){return"Admin"===e.user.role},isStudent(e){return"Student"===e.user.role}},mutations:{SET_USER_DATA(e,t){e.user=t,localStorage.setItem("user",JSON.stringify(t)),B().defaults.headers.common.authorization="Bearer "+t.token},async CLEAR_USER_DATA(e){e.user=null,await localStorage.removeItem("user"),location.reload()}},actions:{login({commit:e},t){return B().post("http://localhost:8001/user/login",null,{params:{email:t.email,password:t.password}}).then((t=>{e("SET_USER_DATA",t.data)}))},logout({commit:e}){e("CLEAR_USER_DATA")}},modules:{}});var Es=$s;const Ts=[{path:"/",redirect:"/subjects"},{path:"/subjects",name:"subjects",component:K,meta:{requiresAuth:!0}},{path:"/subjects/:id",name:"SubjectLayout",props:!0,component:Ie,meta:{requiresAuth:!0},children:[{path:"queue",name:"SubjectQueue",component:Fe},{path:"details",name:"SubjectDetails",component:Ee},{path:"assignments",name:"SubjectAssignments",component:st},{path:"subjectUsers",name:"subjectUsers",component:ht},{path:"joinQueue",name:"joinQueue",component:Dt}]},{path:"/login",name:"login",component:Lt},{path:"/register",name:"register",component:Tt},{path:"/admin",name:"admin",props:!0,component:es,meta:{requiresAuth:!0},children:[{path:"users",name:"users",component:as},{path:"subjects",name:"allSubjects",component:Ws}]},{path:"/createSubject",name:"createSubject",component:zs}],Ns=(0,U.p7)({history:(0,U.r5)(),routes:Ts});Ns.beforeEach(((e,t,s)=>{const r=["/login"],o=r.includes(e.path),l=["/admin","/register"],n=l.includes(e.path),i=["/subjects"],u=i.includes(e.path),a=Es.state.user;let d=!1;return a&&(d=Es.getters.isAdmin),e.matched.some((e=>e.meta.requiresAuth))?a?o&&a&&d||n&&!d&&a?s("/subjects"):u&&d&&a||o&&d&&a?s("/admin"):s():s({name:"login"}):s()}));var Zs=Ns,Os=s(4609),Hs=s.n(Os),Qs=s(8420),Rs=s.n(Qs);const Ys=s(1917),Ls=(0,r.ri)(k);Ys.keys().forEach((e=>{const t=Ys(e),s=Hs()(Rs()(e.replace(/^\.\/(.*)\.\w+$/,"$1")));Ls.component(s,t.default||t)})),Ls.use(Es).use(Zs).mount("#app")},5660:function(e,t,s){"use strict";s.r(t),s.d(t,{default:function(){return a}});var r=s(3396);const o=["id"];function l(e,t,s,l,n,i){return(0,r.wg)(),(0,r.iD)("p",{"aria-live":"assertive",class:"errorMessage",id:s.id},[(0,r.WI)(e.$slots,"default",{},void 0,!0)],8,o)}var n={props:{id:{type:[String,Number],required:!0}}},i=s(89);const u=(0,i.Z)(n,[["render",l],["__scopeId","data-v-5373aee2"]]);var a=u},1510:function(e,t,s){"use strict";s.r(t),s.d(t,{default:function(){return b}});var r=s(3396),o=s(7139);const l={class:"container"},n=["for"],i=["id","value","placeholder","aria-describedby","aria-invalid"];function u(e,t,s,u,a,d){const c=(0,r.up)("BaseErrorMessage");return(0,r.wg)(),(0,r.iD)("div",l,[s.label?((0,r.wg)(),(0,r.iD)("label",{key:0,for:u.uuid},(0,o.zw)(s.label),9,n)):(0,r.kq)("",!0),(0,r._)("input",(0,r.dG)({class:"field"},e.$attrs,{onInput:t[0]||(t[0]=t=>e.$emit("update:modelValue",t.target.value)),id:u.uuid,value:s.modelValue,placeholder:s.label,"aria-describedby":s.error?`${u.uuid}-error`:null,"aria-invalid":!!s.error,class:{error:s.error}}),null,16,i),(0,r.Wm)(c,{id:`${u.uuid}-error`},{default:(0,r.w5)((()=>[(0,r.Uk)((0,o.zw)(s.error),1)])),_:1},8,["id"])])}let a=0;function d(){const e=()=>(a++,a);return{getID:e}}var c={props:{label:{type:String,default:""},error:{type:String,default:""},modelValue:{type:[String,Number],default:""}},setup(){const e=d().getID();return{uuid:e}}},m=s(89);const h=(0,m.Z)(c,[["render",u],["__scopeId","data-v-96ed0e94"]]);var b=h},1917:function(e,t,s){var r={"./BaseErrorMessage.vue":5660,"./BaseInput.vue":1510};function o(e){var t=l(e);return s(t)}function l(e){if(!s.o(r,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return r[e]}o.keys=function(){return Object.keys(r)},o.resolve=l,e.exports=o,o.id=1917},6949:function(e,t,s){"use strict";e.exports=s.p+"img/logo.d1e80654.png"}},t={};function s(r){var o=t[r];if(void 0!==o)return o.exports;var l=t[r]={id:r,loaded:!1,exports:{}};return e[r](l,l.exports,s),l.loaded=!0,l.exports}s.m=e,function(){var e=[];s.O=function(t,r,o,l){if(!r){var n=1/0;for(d=0;d<e.length;d++){r=e[d][0],o=e[d][1],l=e[d][2];for(var i=!0,u=0;u<r.length;u++)(!1&l||n>=l)&&Object.keys(s.O).every((function(e){return s.O[e](r[u])}))?r.splice(u--,1):(i=!1,l<n&&(n=l));if(i){e.splice(d--,1);var a=o();void 0!==a&&(t=a)}}return t}l=l||0;for(var d=e.length;d>0&&e[d-1][2]>l;d--)e[d]=e[d-1];e[d]=[r,o,l]}}(),function(){s.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(t,{a:t}),t}}(),function(){s.d=function(e,t){for(var r in t)s.o(t,r)&&!s.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){s.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){s.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){s.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){s.p="/"}(),function(){var e={143:0};s.O.j=function(t){return 0===e[t]};var t=function(t,r){var o,l,n=r[0],i=r[1],u=r[2],a=0;if(n.some((function(t){return 0!==e[t]}))){for(o in i)s.o(i,o)&&(s.m[o]=i[o]);if(u)var d=u(s)}for(t&&t(r);a<n.length;a++)l=n[a],s.o(e,l)&&e[l]&&e[l][0](),e[l]=0;return s.O(d)},r=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=s.O(void 0,[998],(function(){return s(8764)}));r=s.O(r)})();
//# sourceMappingURL=app.deea7671.js.map