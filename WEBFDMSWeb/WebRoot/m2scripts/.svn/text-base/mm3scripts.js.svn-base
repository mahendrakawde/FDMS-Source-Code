function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) {d=document;} if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) {x=d.all[n];} for (i=0;!x&&i<d.forms.length;i++) {x=d.forms[i][n];}
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) {x=MM_findObj(n,d.layers[i].document);}
  if(!x && d.getElementById) {x=d.getElementById(n);} return x;
}

//MenuMagic II scripts by PVII
//www.projectseven.com
//Copyright(c) 2002, All Rights Reserved
//
function P8_setMM2(){ //v2.0 by PVII
 //set the image over and down name convention
 document.P8TabOver="_over";
 document.P8TabDown="_down";
 var dt=false;if(document.getElementsByTagName){dt=true;}if(document.P8TabBar){return;}
 var i,k=-1,g,x,gg,tl,ts,ti,tm,tt,tsn,tu,el,args=P8_setMM2.arguments;
 P8TabProp=new Array();for(i=0;i<args.length;i++){P8TabProp[i]=args[i];}
 P8TabIM=new Array();P8TabSB=new Array();if(dt){tm=document.getElementsByTagName("IMG");
 }else{tm=document.images;}tm=document.images;tt=new Array();tt=tt.concat(tm);
 if(document.layers){for(i=0;i<document.layers.length;i++){ti=document.layers[i].document.images;
 if(ti){tt=tt.concat(ti);}for(x=0;x<document.layers[i].document.layers.length;x++){
 ti=document.layers[i].document.layers[x].document.images;if(ti){tt=tt.concat(ti);}}}tm=tt;}
 for(i=0;i<tm.length;i++){tl=tm[i].name; if(dt&&!tl){tl=tm[i].id;}
 if(tl.indexOf("P8TBim")==0){ts=tl.replace("P8TBim","");
 tsn="P8TBsub"+ts;k++;P8TabIM[k]=tl;if((g=MM_findObj(tsn))!=null){P8TabSB[k]=tsn;
 gg=(document.layers)?g:g.style;gg.visibility="hidden";}else{P8TabSB[k]='N';}}}
 document.P8_TBswapd=new Array();document.P8_TBswapo=new Array();for(i=0;i<P8TabIM.length;i++){
 g=MM_findObj(P8TabIM[i]);gg=g.src;g.P8TBim=g.src;tu=gg.lastIndexOf(".");
 g.P8TBimo=gg.substring(0,tu)+document.P8TabOver+gg.substring(tu,gg.length);
 g.P8TBimd=gg.substring(0,tu)+document.P8TabDown+gg.substring(tu,gg.length);
 if(P8TabProp[2]>1){document.P8_TBswapo[i]=new Image();document.P8_TBswapo[i].src=g.P8TBimo;}
 if(P8TabProp[2]>0){if(P8TabProp[2]==3){g.P8TBimd=g.P8TBimo;}document.P8_TBswapd[i]=new Image();
 document.P8_TBswapd[i].src=g.P8TBimd;}}if((g=MM_findObj('P8TabH'))!=null){gg=(document.layers)?g:g.style;
 gg.visibility="hidden";}if(dt&&P8TabProp[3]!='none'&&!window.opera){
 g=document.getElementsByTagName("A");for(i=0;i<g.length;i++){if(g[i].hasChildNodes()){el=g[i].firstChild;
 while (el){if(el.nodeType==3){gg=el.nodeValue;if(P8TabProp[3]==gg.replace("\n","")){
 g[i].className=P8TabProp[4];break;}}el=el.firstChild;}}}}document.P8TabBar=true;
}

function P8_trigMM2(bu){ //v2.0 by PVII
 if(!document.P8TabBar){return;}var i,g,d,dB=-1,tF=false,sF=false;
 for(i=0;i<P8TabSB.length;i++){sF=false;if((g=MM_findObj(P8TabSB[i]))!=null){g=MM_findObj(P8TabSB[i]);
 gg=(document.layers)?g:g.style;sF=true;}d=MM_findObj(P8TabIM[i]);if(P8TabIM[i]==P8TabProp[0]){
 dB=i;}if(P8TabIM[i]==bu){tF=true;if(sF){gg.visibility="visible";}if(P8TabProp[2]>0){
 if(i==dB){d.src=d.P8TBimd;}else if (P8TabProp[2]>1){d.src=d.P8TBimo;}}if((g=MM_findObj('P8TabH'))!=null){
 gg=(document.layers)?g:g.style;gg.visibility="visible";}}else{if(sF){gg.visibility="hidden";}
 if(P8TabProp[2]>0&&i!=dB){d.src=d.P8TBim;}}}if(!tF){if(dB>-1){d=MM_findObj(P8TabIM[dB]);
 if((g=MM_findObj(P8TabSB[dB]))!=null&&P8TabProp[1]==0){gg=(document.layers)?g:g.style;
 gg.visibility="visible";}if(P8TabProp[2]>0){d.src=d.P8TBimd;}}
 if((g=MM_findObj('P8TabH'))!=null){gg=(document.layers)?g:g.style;gg.visibility="hidden";}}
}

function P8_downMM2(ta,bu){ //v1.0 by PVII
 if(!document.P8TabBar){return;}
 var tk=document.P8dnmm;if(tk){tk.className='';}
 ta.className=P8TabProp[4];document.P8dnmm=ta;
 P8TabProp[0]=bu;eval("P8_trigMM2('"+bu+"')");
}