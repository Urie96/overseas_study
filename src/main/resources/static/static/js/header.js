document.writeln("    <nav class=\'navbar navbar-default\' role=\'navigation\' id=\'notloggedin\'>");
document.writeln("      <div class=\'container-fluid\'>");
document.writeln("        <ul class=\'nav navbar-nav navbar-right\'>");
document.writeln("          <li><a href=\'login.html\'><span class=\'glyphicon glyphicon-log-in\'></span> 登录</a></li>");
document.writeln("          <li><a href=\'signUp.html\'><span class=\'glyphicon glyphicon-user\'></span> 注册</a></li>");
document.writeln("        </ul>");
document.writeln("      </div>");
document.writeln("    </nav>");
document.writeln("    <nav class=\'navbar navbar-default\' role=\'navigation\' id=\'loggedin\'>");
document.writeln("      <div class=\'container-fluid\'>");
document.writeln("        <ul class=\'nav navbar-nav navbar-right\'>");
document.writeln("          <li><a href=\'userCenter.html\'>欢迎您,<span class=\'username\'>********</span></a></li>");
document.writeln("          <li><a href=\'javascript:void(0)\' onclick=\'logout()\'><span class=\'glyphicon glyphicon-off\'></span>退出</a></li>");
document.writeln("        </ul>");
document.writeln("      </div>");
document.writeln("    </nav>");
document.writeln("    <div class=\'container\' >");
document.writeln("      <div class=\'col-sm-3\'>");
document.writeln("        <img src=\'imgs/logo.png\' alt=\'LOGO\' width=\'260\' style=\'margin-top: 20px;opacity: 0.8;cursor:pointer\' onclick=\'toIndexPage()\' >");
document.writeln("      </div>");
document.writeln("      <div class=\'col-sm-5 col-sm-offset-1\' style=\'padding-top: 50px;padding-bottom: 70px\'>");
document.writeln("        <div class=\'input-group\'>");
document.writeln("          <span class=\'input-group-addon\'><span class=\'glyphicon glyphicon-search\'></span></span>");
document.writeln("          <input type=\'text\' class=\'form-control\' id=\'searchInput\' placeholder=\'慕尼黑\'>");
document.writeln("          <div class=\'input-group-btn\'>");
document.writeln("            <button type=\'button\' class=\'btn btn-default\' tabindex=\'-1\' id=\'searchBtn\' searchWhat=\'schools\' onclick=\'search()\'>搜院校");
document.writeln("            </button>");
document.writeln("            <button type=\'button\' class=\'btn btn-default dropdown-toggle\' data-toggle=\'dropdown\' tabindex=\'-1\'>");
document.writeln("              <span class=\'caret\'></span>");
document.writeln("              <span class=\'sr-only\'>切换下拉菜单</span>");
document.writeln("            </button>");
document.writeln("            <ul class=\'dropdown-menu pull-right\'>");
document.writeln("              <li><a href=\'javascript:void(0);\' onclick=\'shiftSearch()\' id=\'shiftSearch\'>搜问答</a></li>");
document.writeln("            </ul>");
document.writeln("          </div><!-- /btn-group -->");
document.writeln("        </div>");
document.writeln("      </div>");
document.writeln("      <div class=\'col-sm-2 col-sm-offset-1\'>");
document.writeln("        <img src=\'http://img.lcwcdn.com/u/51images/lcwtel.png\' alt=\'电话咨询\' width=\'240\' style=\'margin-top: 40px;opacity: 0.8\'>");
document.writeln("      </div>");
document.writeln("    </div>");
document.writeln("    <div style=\'background-color:#dddddd\'>");
document.writeln("      <div class=\'container\'>");
document.writeln("        <div class=\'row\'>");
document.writeln("          <div class=\'col-sm-6\'>");
document.writeln("            <ul class=\'nav nav-pills nav-justified\'>");
document.writeln("              <li id=\'nav_index\'><a href=\'index.html\' style=\'font-weight: bold;font-size:  16px\'>主页</a></li>");
document.writeln("              <li id=\'nav_school\'><a href=\'schools.html\' style=\'font-weight: bold;font-size:  16px\'>院校</a></li>");
document.writeln("              <li id=\'nav_assess\'><a href=\'assessNew.html\' style=\'font-weight: bold;font-size: 16px\'>评估</a></li>");
document.writeln("              <li id=\'nav_qa\'><a href=\'qa.html\' style=\'font-weight: bold;font-size: 16px\'>问答</a></li>");
document.writeln("            </ul>");
document.writeln("          </div>");
document.writeln("        </div>");
document.writeln("      </div>");
document.writeln("    </div>");
document.writeln("    <script>");
document.writeln("        $(\'#loggedin\').hide();");
document.writeln("        $.ajax({");
document.writeln("          type: \'get\',");
document.writeln("          url: \'uname.json\',");
document.writeln("          async: true,");
document.writeln("          success: function(result){");
document.writeln("              if (result.code==100){ ");
document.writeln("                $(\'#notloggedin\').hide();");
document.writeln("                $(\'#loggedin\').show();");
document.writeln("                $(\'.username\').text(result.extend.name);");
document.writeln("              }");
document.writeln("          }");
document.writeln("        });");
document.writeln("    </script>");