/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function changeTab(evt, tabName) {
    var i, tabcontent, tablinks, defaultTab;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    defaultTab = document.getElementsByClassName("defaultTab");
    if (evt.currentTarget.className === undefined) {
        document.getElementById("defaultTab").className += " active";
    } else {
        evt.currentTarget.className += " active";
    }
}