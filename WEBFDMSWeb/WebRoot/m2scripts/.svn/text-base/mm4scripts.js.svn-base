function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) {d=document;} if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) {x=d.all[n];} for (i=0;!x&&i<d.forms.length;i++) {x=d.forms[i][n];}
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) {x=MM_findObj(n,d.layers[i].document);}
  if(!x && d.getElementById) {x=d.getElementById(n); } return x;
}

//MenuMagic II scripts by PVII
//www.projectseven.com
//Copyright(c) 2002, All Rights Reserved
//
function p9_setMM2(){ //v2.0 by PVII
 //set the image over and down name convention
 document.p9TabOver="_over";
 document.p9TabDown="_down";
 var dt=false;
if(document.getElementsByTagName){dt=true;}
if(document.p9TabBar){return;}
 var i,k=-1,g,x,gg,tl,ts,ti,tm,tt,tsn,tu,el,args=p9_setMM2.arguments;

 p9TabProp=new Array();for(i=0;i<args.length;i++){p9TabProp[i]=args[i];}
 p9TabIM=new Array();p9TabSB=new Array();if(dt){tm=document.getElementsByTagName("IMG");
 }else{tm=document.images;}tm=document.images;tt=new Array();tt=tt.concat(tm);
 if(document.layers){for(i=0;i<document.layers.length;i++){ti=document.layers[i].document.images;
 if(ti){tt=tt.concat(ti);}for(x=0;x<document.layers[i].document.layers.length;x++){
 ti=document.layers[i].document.layers[x].document.images;if(ti){tt=tt.concat(ti);}}}tm=tt;}
 for(i=0;i<tm.length;i++){tl=tm[i].name; if(dt&&!tl){tl=tm[i].id;}
 if(tl.indexOf("p9TBim")==0){ts=tl.replace("p9TBim","");
 tsn="p9TBsub"+ts;k++;p9TabIM[k]=tl;if((g=MM_findObj(tsn))!=null){p9TabSB[k]=tsn;
 gg=(document.layers)?g:g.style;gg.visibility="hidden";}else{p9TabSB[k]='N';}}}
 document.p9_TBswapd=new Array();document.p9_TBswapo=new Array();for(i=0;i<p9TabIM.length;i++){
 g=MM_findObj(p9TabIM[i]);gg=g.src;g.p9TBim=g.src;tu=gg.lastIndexOf(".");
 g.p9TBimo=gg.substring(0,tu)+document.p9TabOver+gg.substring(tu,gg.length);
 g.p9TBimd=gg.substring(0,tu)+document.p9TabDown+gg.substring(tu,gg.length);
 if(p9TabProp[2]>1){document.p9_TBswapo[i]=new Image();document.p9_TBswapo[i].src=g.p9TBimo;}
 if(p9TabProp[2]>0){if(p9TabProp[2]==3){g.p9TBimd=g.p9TBimo;}document.p9_TBswapd[i]=new Image();
 document.p9_TBswapd[i].src=g.p9TBimd;}}if((g=MM_findObj('p9TabH'))!=null){gg=(document.layers)?g:g.style;
 gg.visibility="hidden";}if(dt&&p9TabProp[3]!='none'&&!window.opera){
 g=document.getElementsByTagName("A");for(i=0;i<g.length;i++){if(g[i].hasChildNodes()){el=g[i].firstChild;
 while (el){if(el.nodeType==3){gg=el.nodeValue;if(p9TabProp[3]==gg.replace("\n","")){
 g[i].className=p9TabProp[4];break;}}el=el.firstChild;}}}}document.p9TabBar=true;
}

function p9_trigMM2(bu){ //v2.0 by PVII
 if(!document.p9TabBar){return;}var i,g,d,dB=-1,tF=false,sF=false;
 for(i=0;i<p9TabSB.length;i++){sF=false;if((g=MM_findObj(p9TabSB[i]))!=null){g=MM_findObj(p9TabSB[i]);
 gg=(document.layers)?g:g.style;sF=true;}d=MM_findObj(p9TabIM[i]);if(p9TabIM[i]==p9TabProp[0]){
 dB=i;}if(p9TabIM[i]==bu){tF=true;if(sF){gg.visibility="visible";}if(p9TabProp[2]>0){
 if(i==dB){d.src=d.p9TBimd;}else if (p9TabProp[2]>1){d.src=d.p9TBimo;}}if((g=MM_findObj('p9TabH'))!=null){
 gg=(document.layers)?g:g.style;gg.visibility="visible";}}else{if(sF){gg.visibility="hidden";}
 if(p9TabProp[2]>0&&i!=dB){d.src=d.p9TBim;}}}if(!tF){if(dB>-1){d=MM_findObj(p9TabIM[dB]);
 if((g=MM_findObj(p9TabSB[dB]))!=null&&p9TabProp[1]==0){gg=(document.layers)?g:g.style;
 gg.visibility="visible";}if(p9TabProp[2]>0){d.src=d.p9TBimd;}}
 if((g=MM_findObj('p9TabH'))!=null){gg=(document.layers)?g:g.style;gg.visibility="hidden";}}
}

function p9_downMM2(ta,bu){ //v1.0 by PVII
 if(!document.p9TabBar){return;}
 var tk=document.p9dnmm;if(tk){tk.className='';}
 ta.className=p9TabProp[4];document.p9dnmm=ta;
 p9TabProp[0]=bu;eval("p9_trigMM2('"+bu+"')");
}