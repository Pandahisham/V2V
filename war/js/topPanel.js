$(document).ready(
    function() {

      // This selector will be reused when selecting actual tab widget
      // elements.
      var tab_a_selector = 'ul.ui-tabs-nav a';

      var topPanelTabs = $("#topPanelTabs").tabs({
        cache : true
      });
      var donorsTabs = $("#donorsTab").tabs({
//        cache : true,
        // select event is deprecated in jquery 1.9. Use it for now.
        select: function(event, ui) {
                  var tabElement = $(ui.tab)[0];
                  showLoadingImage(tabElement.hash.substr(1));
                }
      });
      var collectionsTabs = $("#collectionsTab").tabs({
//        cache : true
      });
      var testResultsTabs = $("#testResultsTab").tabs({
//        cache : true
      });
      var productsTabs = $("#productsTab").tabs({
//        cache : true
      });
      var requestsTabs = $("#requestsTab").tabs({
//        cache : true
      });
      var usageTabs = $("#usageTab").tabs({
//        cache : true
      });
      var reportsTabs = $("#reportsTab").tabs({
//        cache : true
      });
      var adminTabs = $("#adminTab").tabs({
//      cache : true
      });

      console.log($(".leftPanel"));
      $(".leftPanel").on("tabsbeforeActivate",
                           function(event, ui) {
                             console.log("here");
                             console.log(ui.tab);
                           });
      
      // Define our own click handler for the tabs, overriding the default.
      $(".tabs").find(tab_a_selector).click(function(event, ui) {
        var t = getSelectedTabs();
        console.log("pushstate called " + JSON.stringify(t));
        history.pushState(t, "", "");
        return false;
      });

      var pushState = history.pushState;
      history.pushState = function() {
        pushState.apply(history, arguments);
      };

      $(window).bind(
          "popstate",
          function(event) {
            console.log("popstate called");
            console.log(event);
            if (event === undefined || event.originalEvent == undefined
                || event.originalEvent.state == undefined) {
              console.log("state is null");
              return;
            }
            var state = event.originalEvent.state;
            console.log(state);
            if (state == undefined)
              return;

            if (state.topPanelSelected !== undefined) {

              topPanelTabs.tabs("select", state.topPanelSelected);

              var leftPanelTabs;
              if (state.leftPanelSelected !== undefined) {
                switch (state.topPanelSelected) {
                case 1:
                  leftPanelTabs = donorsTabs;
                  break;
                case 2:
                  leftPanelTabs = collectionsTabs;
                  break;
                case 3:
                  leftPanelTabs = testResultsTabs;
                  break;
                case 4:
                  leftPanelTabs = productsTabs;
                  break;
                case 5:
                  leftPanelTabs = requestsTabs;
                  break;
                case 6:
                  leftPanelTabs = usageTabs;
                  break;
                case 7:
                  leftPanelTabs = reportsTabs;
                  break;
                case 7:
                  leftPanelTabs = adminTabs;
                  break;
                default:
                  break;
                }

                leftPanelTabs.tabs("select", state.leftPanelSelected);
                if (state.targetId !== undefined
                    && state.oldRequestUrl !== undefined) {
                  var data = {};
                  // there may or may not be data to send data for this
                  // request
                  // the data may already be encoded as part of the URL
                  // string
                  if (state.oldRequestData !== undefined) {
                    data = state.oldRequestData;
                  }
                  showLoadingImage(state.targetId);
                  $.ajax({
                    url : state.oldRequestUrl,
                    data : data,
                    method : "GET",
                    success : function(responseData) {
                      $('#' + state.targetId).html(responseData);
                    }
                  });
                } else {
                  leftPanelTabs.tabs("load", state.leftPanelSelected);
                }

              }
            }
          });

      // for initial page load
      history.pushState({topPanelSelected : 0}, "", "")
    });