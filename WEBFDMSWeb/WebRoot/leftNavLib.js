   function WM_toggle(id) {
      var startLeft = document.all.mainDiv.offsetLeft +document.all.mainTable.offsetLeft+document.all.menubar.offsetLeft;
      if (document.all.mainTable.border > "0") {
         startLeft = startLeft + 4;
      }
      for (var i=1; i < id; i++) {
	     startLeft = startLeft + document.all['menu' +id +'Image'].width;
	  }
	  document.all['menu' +id +'Options'].style.pixelLeft = startLeft;

      WM_toggleRest(id)
      if (document.all) {
         if (document.all['menu' +id +'Options'].style.display == 'none') {
             document.all['menu' +id +'Options'].style.display = 'block';
         } else {
             document.all['menu' +id +'Options'].style.display = 'none';
         }
         return false; 
      } else {
         if (document.getElementById) {
            if (document.getElementById('menu' +id +'Options').style.display == 'none') {
               document.getElementById('menu' +id +'Options').style.display = 'block';
            } else {
               document.getElementById('menu' +id +'Options').style.display = 'none';
            }
            return false;
         }
      }
   }
  
   function WM_toggleRest(id){
      if (document.all) {
         if (id != '1') {
            document.all['menu1Options'].style.display = 'none';
         }
         if (id != '2') {
            document.all['menu2Options'].style.display = 'none';
         }
      } else {
         if (document.getElementById) {
            if (id != '1') {
               document.getElementById('menu1Options').style.display = 'none';
            }
            if (id != '2') {
               document.getElementById('menu2Options').style.display = 'none';
            }
         }
      }
   }