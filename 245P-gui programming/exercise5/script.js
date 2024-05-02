function setupTabs() {
    document.querySelectorAll(".tabs__button").forEach((button) => {
      button.addEventListener("click", () => {

        //find .tabs_sidebar
        const sideBar=button.parentElement;

        // find .tabs
        const tabsContainer=sideBar.parentElement;
       
        // find which tab is clicked
        const tabNumber = button.dataset.forTab;

        console.log("click event on button"+tabNumber);

        //get first tabs_content which has datatab=${tabNumber} 
        const tableActivate=tabsContainer.querySelector(`.tabs__content[data-tab="${tabNumber}"]`);
                            

        //remove the former button
        sideBar.querySelectorAll(".tabs__button").forEach((button)=>{
            button.classList.remove("tabs__button--active");
        });

        //remove the former element
        tabsContainer.querySelectorAll(".tabs__content").forEach((tab)=>{
            tab.classList.remove("tabs__content--active");
        })

        button.classList.add("tabs__button--active");
        tableActivate.classList.add("tabs__content--active");
      });
    });
  }


document.addEventListener("DOMContentLoaded", () => {
    setupTabs();
});