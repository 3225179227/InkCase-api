/*
 Navicat Premium Data Transfer

 Source Server         : ink-api
 Source Server Type    : MySQL
 Source Server Version : 50725 (5.7.25)
 Source Host           : 110.41.0.92:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50725 (5.7.25)
 File Encoding         : 65001

 Date: 27/04/2024 22:18:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL,
  `comment_counts` int(11) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `summary` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_counts` int(11) NULL DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `author_id` bigint(20) NULL DEFAULT NULL,
  `body_id` bigint(20) NULL DEFAULT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `tags_str` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKndx2m69302cso79y66yxiju4h`(`author_id`) USING BTREE,
  INDEX `FKrd11pjsmueckfrh9gs7bc6374`(`body_id`) USING BTREE,
  INDEX `FKjrn3ua4xmiulp8raj7m9d2xk6`(`category_id`) USING BTREE,
  CONSTRAINT `FKjrn3ua4xmiulp8raj7m9d2xk6` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKndx2m69302cso79y66yxiju4h` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrd11pjsmueckfrh9gs7bc6374` FOREIGN KEY (`body_id`) REFERENCES `article_body` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (9, 0, '2024-02-01 14:37:23', 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。', 'Vue.js 是什么', 3, 0, 1, 20, 1, '');
INSERT INTO `article` VALUES (10, 0, '2024-03-01 14:47:19', '本节将介绍如何在项目中使用 Element。', 'Element相关', 5, 0, 1, 21, 1, '');
INSERT INTO `article` VALUES (1784223545905221633, 0, '2024-04-28 07:10:17', '中国最大江海移动船坞——4.5万吨宽扁浅吃水型半潜驳“四航永兴”号在江苏通州湾成功运输全国最大最重海上风电安装平台浮装出海。', '“四航永兴”号在最大最重海上风电安装平台浮装出海', 2, 0, 1783892409089888257, 1784223545863278594, 6, '运输,船舶');
INSERT INTO `article` VALUES (1784223769805557761, 0, '2024-04-28 07:11:11', '中国公安部公布10起打击网络谣言违法犯罪典型案例,其中包括浙江、江苏公安机关查处“寒假作业丢巴黎”摆拍引流案。', '寒假作业丢巴黎”事件系编造！官方通报', 1, 0, 1783892409089888257, 1784223769772003330, 7, '谣言,网络,摆拍');
INSERT INTO `article` VALUES (1784224045635571713, 0, '2024-04-28 07:12:16', '中国第135届中国进出口商品交易会(简称“广交会”)将于4月15日至5月5日在广州举办,线上平台继续常态化运营。', '第135届广交会将于4月15日在广州举办', 3, 0, 1783891596569317377, 1784224045589434369, 5, '广交会,企业,贸易');
INSERT INTO `article` VALUES (1784224266503426050, 0, '2024-04-28 07:13:09', '中国国务院总理李强4月7日上午在北京人民大会堂会见美国财政部长耶伦。', '李强会见美国财政部长', 1, 0, 1783891596569317377, 1784224266474065922, 6, '合作,双方,全球');
INSERT INTO `article` VALUES (1784225211895353346, 0, '2024-04-28 07:16:54', '介绍 Java 开发框架(Spring)的优点。', 'Spring基础', 6, 0, 1783891596569317377, 1784225211857604609, 2, 'Spring,框架,编程');

-- ----------------------------
-- Table structure for article_body
-- ----------------------------
DROP TABLE IF EXISTS `article_body`;
CREATE TABLE `article_body`  (
  `id` bigint(20) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content_html` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_body
-- ----------------------------
INSERT INTO `article_body` VALUES (1, '## 2024-01-04\n\n```\n# 使用vue的Webpack模板生成脚手架\n```\n\n## 2018-01-05\n\n```\n# 引入ElementUI\n\n# babel-plugin-component自定义主题\n# 首页\n# 登陆页\n# 注册页面\n# 日志页\n```\n## 2018-01-07\n\n```\n# 调整底部栏始终固定在底部\n# 日志页 添加时间轴\n# 首页的文章列表\n```\n## 2018-01-08\n\n```\n# 使用组件-博客作者tab页 \n# 添加第三方图标\n```\n\n## 2018-01-09\n\n```\n# 调整顶部导航栏：激活文字颜色，click点击\n# 组件-最新文章tab页\n\n# 最新文章、最热文章使用相同组件\n# 底部栏设计\n# 页面与两边边距改为100\n```\n\n## 2018-01-10\n\n```\n# 写博客 引入mavonEditor编辑器\n# 顶部导航栏都放入一个Menu中\n# 写文章页面\n#　mavonEditor局部引入\n\n#　页面的中间区域固定宽度，自动居中\n# 发布和取消\n# 发布dialog\n\n```\n## 2018-01-11\n\n```\n# 文章组件用守卫来改变body背景色\n# 调整登陆和注册页面，使其居中\n\n#子页面调整根元素为div\n#文章详情页\n\n```\n## 2018-01-12\n\n```\n# 文章详情页  内容  评论等\n\n```\n## 2018-01-13\n\n```\n## 重新调整页面结构	\n#顶部和底部 抽成  BaseHeader BaseFooter 组件\n#BlogView为单独页，以前是Home的子路由\n\n```\n## 2018-01-15\n\n``` \n# 文章分类去掉子级\n# 将首页的文章列表抽成 ArticleItem组件\n# 增加文章的评论展示\n# 增加文章分类、标签页\n\n```\n\n## 2018-01-15  2\n\n``` \n# 回到顶部去掉过渡动画（影响顶部导航栏）\n# 顶部导航栏 增加登录后菜单\n# 首页增加 最热标签\n# 增加 文章分类 标签的详情页\n# 将文章详情页、 文章分类标签页 改为Home的子路由（以前单独页）\n# Home组件增加路由判断：更正导航栏的状态、条件显示底部栏\n\n```\n\n## 2018-01-16\n\n``` \n# 将写文章的顶部Header合并到BaseHeader中\n# 图片都放到了static目录下\n\n```\n\n## 2018-01-24\n\n``` \n# 将自定义的theme放到assets下\n# 加入axios\n# 加入vuex\n# 实现登录\n# 实现退出\n\n```\n\n## 2018-01-25\n\n``` \n# 实现注册逻辑\n# 写文章功能实现\n# 写文章时支持插入图片\n\n```\n## 2018-01-26\n\n``` \n# 引入lodash工具类\n# 优化写文章的工具栏：滚动时固定顶部\n# 写文章 后台获取文章分类和标签\n\n# 首页的文章列表\n\n```\n\n## 2018-01-27\n\n``` \n# 修改首页文章列表的样式\n# 首页加载文章功能\n# 文章查看功能\n# 文章分类和标签功能列表\n\n```\n\n## 2018-01-28\n\n``` \n# 文章分类和标签详情\n\n```\n\n## 2018-01-29\n\n``` \n# 文章分类和标签的文章数\n# 首页最热文章\n# 首页最新文章\n# 首页最热标签\n\n```\n## 2018-01-30\n\n``` \n# BaseHeader放回views中\n# 修改Axios后置拦截，全局处理错误\n# 将登录 退出 和头像 放到一起\n\n```', '<h2>2018-01-04</h2>\n<pre><code class=\"lang-\"># 使用vue的Webpack模板生成脚手架\n</code></pre>\n<h2>2018-01-05</h2>\n<pre><code class=\"lang-\"># 引入ElementUI\n\n# babel-plugin-component自定义主题\n# 首页\n# 登陆页\n# 注册页面\n# 日志页\n</code></pre>\n<h2>2018-01-07</h2>\n<pre><code class=\"lang-\"># 调整底部栏始终固定在底部\n# 日志页 添加时间轴\n# 首页的文章列表\n</code></pre>\n<h2>2018-01-08</h2>\n<pre><code class=\"lang-\"># 使用组件-博客作者tab页 \n# 添加第三方图标\n</code></pre>\n<h2>2018-01-09</h2>\n<pre><code class=\"lang-\"># 调整顶部导航栏：激活文字颜色，click点击\n# 组件-最新文章tab页\n\n# 最新文章、最热文章使用相同组件\n# 底部栏设计\n# 页面与两边边距改为100\n</code></pre>\n<h2>2018-01-10</h2>\n<pre><code class=\"lang-\"># 写博客 引入mavonEditor编辑器\n# 顶部导航栏都放入一个Menu中\n# 写文章页面\n#　mavonEditor局部引入\n\n#　页面的中间区域固定宽度，自动居中\n# 发布和取消\n# 发布dialog\n\n</code></pre>\n<h2>2018-01-11</h2>\n<pre><code class=\"lang-\"># 文章组件用守卫来改变body背景色\n# 调整登陆和注册页面，使其居中\n\n#子页面调整根元素为div\n#文章详情页\n\n</code></pre>\n<h2>2018-01-12</h2>\n<pre><code class=\"lang-\"># 文章详情页  内容  评论等\n\n</code></pre>\n<h2>2018-01-13</h2>\n<pre><code class=\"lang-\">## 重新调整页面结构	\n#顶部和底部 抽成  BaseHeader BaseFooter 组件\n#BlogView为单独页，以前是Home的子路由\n\n</code></pre>\n<h2>2018-01-15</h2>\n<pre><code class=\"lang-\"># 文章分类去掉子级\n# 将首页的文章列表抽成 ArticleItem组件\n# 增加文章的评论展示\n# 增加文章分类、标签页\n\n</code></pre>\n<h2>2018-01-15  2</h2>\n<pre><code class=\"lang-\"># 回到顶部去掉过渡动画（影响顶部导航栏）\n# 顶部导航栏 增加登录后菜单\n# 首页增加 最热标签\n# 增加 文章分类 标签的详情页\n# 将文章详情页、 文章分类标签页 改为Home的子路由（以前单独页）\n# Home组件增加路由判断：更正导航栏的状态、条件显示底部栏\n\n</code></pre>\n<h2>2018-01-16</h2>\n<pre><code class=\"lang-\"># 将写文章的顶部Header合并到BaseHeader中\n# 图片都放到了static目录下\n\n</code></pre>\n<h2>2018-01-24</h2>\n<pre><code class=\"lang-\"># 将自定义的theme放到assets下\n# 加入axios\n# 加入vuex\n# 实现登录\n# 实现退出\n\n</code></pre>\n<h2>2018-01-25</h2>\n<pre><code class=\"lang-\"># 实现注册逻辑\n# 写文章功能实现\n# 写文章时支持插入图片\n\n</code></pre>\n<h2>2018-01-26</h2>\n<pre><code class=\"lang-\"># 引入lodash工具类\n# 优化写文章的工具栏：滚动时固定顶部\n# 写文章 后台获取文章分类和标签\n\n# 首页的文章列表\n\n</code></pre>\n<h2>2018-01-27</h2>\n<pre><code class=\"lang-\"># 修改首页文章列表的样式\n# 首页加载文章功能\n# 文章查看功能\n# 文章分类和标签功能列表\n\n</code></pre>\n<h2>2018-01-28</h2>\n<pre><code class=\"lang-\"># 文章分类和标签详情\n\n</code></pre>\n<h2>2018-01-29</h2>\n<pre><code class=\"lang-\"># 文章分类和标签的文章数\n# 首页最热文章\n# 首页最新文章\n# 首页最热标签\n\n</code></pre>\n<h2>2018-01-30</h2>\n<pre><code class=\"lang-\"># BaseHeader放回views中\n# 修改Axios后置拦截，全局处理错误\n# 将登录 退出 和头像 放到一起\n\n</code></pre>\n');
INSERT INTO `article_body` VALUES (20, 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。\n\n如果你想在深入学习 Vue 之前对它有更多了解，我们制作了一个视频，带您了解其核心概念和一个示例工程。\n如果你已经是有经验的前端开发者，想知道 Vue 与其它库/框架有哪些区别，请查看对比其它框架。\n\n# 起步\n\n> 官方指南假设你已了解关于 HTML、CSS 和 JavaScript 的中级知识。如果你刚开始学习前端开发，将框架作为你的第一步可能不是最好的主意——掌握好基础知识再来吧！之前有其它框架的使用经验会有帮助，但这不是必需的。\n\n尝试 Vue.js 最简单的方法是使用 JSFiddle 上的 Hello World 例子。你可以在浏览器新标签页中打开它，跟着例子学习一些基础用法。或者你也可以创建一个 .html 文件，然后通过如下方式引入 Vue：\n\n```javascript\n<script src=\"https://cdn.jsdelivr.net/npm/vue\"></script>\n\n```\n安装教程给出了更多安装 Vue 的方式。请注意我们不推荐新手直接使用 vue-cli，尤其是在你还不熟悉基于 Node.js 的构建工具时。\n\n# 声明式渲染\nVue.js 的核心是一个允许采用简洁的模板语法来声明式地将数据渲染进 DOM 的系统：\n```javascript\n<div id=\"app\">\n  {{ message }}\n</div>\n\n```\n```javascript\nvar app = new Vue({\n  el: \'#app\',\n  data: {\n    message: \'Hello Vue!\'\n  }\n})\n\n```\n我们已经成功创建了第一个 Vue 应用！看起来这跟渲染一个字符串模板非常类似，但是 Vue 在背后做了大量工作。现在数据和 DOM 已经被建立了关联，所有东西都是响应式的。我们要怎么确认呢？打开你的浏览器的 JavaScript 控制台 (就在这个页面打开)，并修改 app.message 的值，你将看到上例相应地更新。\n\n除了文本插值，我们还可以像这样来绑定元素特性：\n\n\n\n\n\n\n', '<p>Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。</p>\n<p>如果你想在深入学习 Vue 之前对它有更多了解，我们制作了一个视频，带您了解其核心概念和一个示例工程。<br />\n如果你已经是有经验的前端开发者，想知道 Vue 与其它库/框架有哪些区别，请查看对比其它框架。</p>\n<h1>起步</h1>\n<blockquote>\n<p>官方指南假设你已了解关于 HTML、CSS 和 JavaScript 的中级知识。如果你刚开始学习前端开发，将框架作为你的第一步可能不是最好的主意——掌握好基础知识再来吧！之前有其它框架的使用经验会有帮助，但这不是必需的。</p>\n</blockquote>\n<p>尝试 Vue.js 最简单的方法是使用 JSFiddle 上的 Hello World 例子。你可以在浏览器新标签页中打开它，跟着例子学习一些基础用法。或者你也可以创建一个 .html 文件，然后通过如下方式引入 Vue：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\">&lt;script src=<span class=\"hljs-string\">\"https://cdn.jsdelivr.net/npm/vue\"</span>&gt;<span class=\"xml\"><span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">script</span>&gt;</span></span>\n\n</code></div></pre>\n<p>安装教程给出了更多安装 Vue 的方式。请注意我们不推荐新手直接使用 vue-cli，尤其是在你还不熟悉基于 Node.js 的构建工具时。</p>\n<h1>声明式渲染</h1>\n<p>Vue.js 的核心是一个允许采用简洁的模板语法来声明式地将数据渲染进 DOM 的系统：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\">&lt;div id=<span class=\"hljs-string\">\"app\"</span>&gt;\n  {{ message }}\n&lt;<span class=\"hljs-regexp\">/div&gt;\n\n</span></code></div></pre>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\"><span class=\"hljs-keyword\">var</span> app = <span class=\"hljs-keyword\">new</span> Vue({\n  <span class=\"hljs-attr\">el</span>: <span class=\"hljs-string\">\'#app\'</span>,\n  <span class=\"hljs-attr\">data</span>: {\n    <span class=\"hljs-attr\">message</span>: <span class=\"hljs-string\">\'Hello Vue!\'</span>\n  }\n})\n\n</code></div></pre>\n<p>我们已经成功创建了第一个 Vue 应用！看起来这跟渲染一个字符串模板非常类似，但是 Vue 在背后做了大量工作。现在数据和 DOM 已经被建立了关联，所有东西都是响应式的。我们要怎么确认呢？打开你的浏览器的 JavaScript 控制台 (就在这个页面打开)，并修改 app.message 的值，你将看到上例相应地更新。</p>\n<p>除了文本插值，我们还可以像这样来绑定元素特性：</p>\n');
INSERT INTO `article_body` VALUES (21, '## 快速上手\n\n本节将介绍如何在项目中使用 Element。\n\n### 使用 Starter Kit\n我们提供了通用的项目模板，你可以直接使用。对于 Laravel 用户，我们也准备了相应的模板，同样可以直接下载使用。\n\n如果不希望使用我们提供的模板，请继续阅读。\n\n### 使用 vue-cli\n\n我们还可以使用 vue-cli 初始化项目，命令如下：\n\n```language\n> npm i -g vue-cli\n> mkdir my-project && cd my-project\n> vue init webpack\n> npm i && npm i element-ui\n```\n\n### 引入 Element\n你可以引入整个 Element，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 Element。\n\n#### 完整引入\n在 main.js 中写入以下内容：\n```javascript\nimport Vue from \'vue\'\nimport ElementUI from \'element-ui\'\nimport \'element-ui/lib/theme-chalk/index.css\'\nimport App from \'./App.vue\'\n\nVue.use(ElementUI)\n\nnew Vue({\n  el: \'#app\',\n  render: h => h(App)\n})\n\n```\n以上代码便完成了 Element 的引入。需要注意的是，样式文件需要单独引入。\n\n#### 按需引入\n借助 babel-plugin-component，我们可以只引入需要的组件，以达到减小项目体积的目的。\n\n首先，安装 babel-plugin-component：\n\n', '<h2>快速上手</h2>\n<p>本节将介绍如何在项目中使用 Element。</p>\n<h3>使用 Starter Kit</h3>\n<p>我们提供了通用的项目模板，你可以直接使用。对于 Laravel 用户，我们也准备了相应的模板，同样可以直接下载使用。</p>\n<p>如果不希望使用我们提供的模板，请继续阅读。</p>\n<h3>使用 vue-cli</h3>\n<p>我们还可以使用 vue-cli 初始化项目，命令如下：</p>\n<pre><code class=\"lang-language\">&gt; npm i -g vue-cli\n&gt; mkdir my-project &amp;&amp; cd my-project\n&gt; vue init webpack\n&gt; npm i &amp;&amp; npm i element-ui\n</code></pre>\n<h3>引入 Element</h3>\n<p>你可以引入整个 Element，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 Element。</p>\n<h4>完整引入</h4>\n<p>在 main.js 中写入以下内容：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\"><span class=\"hljs-keyword\">import</span> Vue <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'vue\'</span>\n<span class=\"hljs-keyword\">import</span> ElementUI <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'element-ui\'</span>\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-string\">\'element-ui/lib/theme-chalk/index.css\'</span>\n<span class=\"hljs-keyword\">import</span> App <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'./App.vue\'</span>\n\nVue.use(ElementUI)\n\n<span class=\"hljs-keyword\">new</span> Vue({\n  <span class=\"hljs-attr\">el</span>: <span class=\"hljs-string\">\'#app\'</span>,\n  <span class=\"hljs-attr\">render</span>: <span class=\"hljs-function\"><span class=\"hljs-params\">h</span> =&gt;</span> h(App)\n})\n\n</code></div></pre>\n<p>以上代码便完成了 Element 的引入。需要注意的是，样式文件需要单独引入。</p>\n<h4>按需引入</h4>\n<p>借助 babel-plugin-component，我们可以只引入需要的组件，以达到减小项目体积的目的。</p>\n<p>首先，安装 babel-plugin-component：</p>\n');
INSERT INTO `article_body` VALUES (1784223545863278594, '本报北京4月24日电 （记者韩鑫）4月23日，全球最大江海移动船坞——4.5万吨宽扁浅吃水型半潜驳“四航永兴”号在江苏通州湾成功运输全国最大最重海上风电安装平台浮装出海。\n\n据介绍，这艘“超级海上移动船坞”由中交四航局自主研发设计建造，船长164米，甲板面积10660平方米，最大下潜深度26.8米，总体参数达到世界一流水平。\n\n“四航永兴”号拥有优异的宽扁浅吃水性能，可在近海航区进行下潜、起浮作业，不仅能用于海底隧道大型管节运输、修造船舶的船坞、大型沉箱的预制平台，还可应用于海上大型构件的拖航运输、工程船舶的调遣运输和海上施工救援的作业平台。\n\n据了解，此次完成出运的为新一代深远海大型风电安装平台，下水自重达2.2万吨，是目前国内单体发运下水重量最大的风电安装平台。', '<p>本报北京4月24日电 （记者韩鑫）4月23日，全球最大江海移动船坞——4.5万吨宽扁浅吃水型半潜驳“四航永兴”号在江苏通州湾成功运输全国最大最重海上风电安装平台浮装出海。</p>\n<p>据介绍，这艘“超级海上移动船坞”由中交四航局自主研发设计建造，船长164米，甲板面积10660平方米，最大下潜深度26.8米，总体参数达到世界一流水平。</p>\n<p>“四航永兴”号拥有优异的宽扁浅吃水性能，可在近海航区进行下潜、起浮作业，不仅能用于海底隧道大型管节运输、修造船舶的船坞、大型沉箱的预制平台，还可应用于海上大型构件的拖航运输、工程船舶的调遣运输和海上施工救援的作业平台。</p>\n<p>据了解，此次完成出运的为新一代深远海大型风电安装平台，下水自重达2.2万吨，是目前国内单体发运下水重量最大的风电安装平台。</p>\n');
INSERT INTO `article_body` VALUES (1784223769772003330, '人民网北京4月13日电 （周静圆）4月12日，浙江省杭州市公安局西湖区分局通报，今年2月在网络热传的“在法国巴黎捡获一年级学生秦朗的寒假作业”事件系策划编造。\n\n通报称，经查，为吸粉引流，网民徐某某与同事薛某共同策划、编造“拾到小学生秦朗丢失的作业本”系列视频脚本，后网购寒假作业本，用手机自拍、制作相关视频，并散播至多个网络平台，造成恶劣影响。目前，公安机关已依法对徐某某、薛某及二人所在公司作出行政处罚。\n\n“求真”栏目了解到，除谣言“策划者”外，还有蹭谣言热度的“冒充者”也受到了法律惩罚。4月12日，公安部官网公布10起打击整治网络谣言违法犯罪典型案例，其中提到了浙江、江苏公安机关查处“寒假作业丢巴黎”摆拍引流案。\n\n2月17日，江苏南通杨某在某网络平台“在法国巴黎捡获一年级学生秦朗的寒假作业”短视频的评论区假冒“秦朗舅舅”进行引流并进行造谣、摆拍、直播，引发“全网寻人”行动。目前，江苏南通公安机关已对杨某处以行政处罚，其账号已关停。\n\n人民数据研究院研究员侯鑫淼表示，个别网络主播为了引流吸粉进行摆拍，编造虚假事件，这会造成不明真相的粉丝效仿，进而扰乱公共秩序，给社会带来极大危害。\n\n治安管理处罚法第二十五条提到，散布谣言，谎报险情、疫情、警情或者以其他方法故意扰乱公共秩序的，处五日以上十日以下拘留，可以并处五百元以下罚款；情节较轻的，处五日以下拘留或者五百元以下罚款。\n\n“网络不是法外之地，希望广大网民朋友擦亮眼睛、理性思考，不造谣、不信谣、不传谣，共建清朗网络家园。”侯鑫淼说。', '<p>人民网北京4月13日电 （周静圆）4月12日，浙江省杭州市公安局西湖区分局通报，今年2月在网络热传的“在法国巴黎捡获一年级学生秦朗的寒假作业”事件系策划编造。</p>\n<p>通报称，经查，为吸粉引流，网民徐某某与同事薛某共同策划、编造“拾到小学生秦朗丢失的作业本”系列视频脚本，后网购寒假作业本，用手机自拍、制作相关视频，并散播至多个网络平台，造成恶劣影响。目前，公安机关已依法对徐某某、薛某及二人所在公司作出行政处罚。</p>\n<p>“求真”栏目了解到，除谣言“策划者”外，还有蹭谣言热度的“冒充者”也受到了法律惩罚。4月12日，公安部官网公布10起打击整治网络谣言违法犯罪典型案例，其中提到了浙江、江苏公安机关查处“寒假作业丢巴黎”摆拍引流案。</p>\n<p>2月17日，江苏南通杨某在某网络平台“在法国巴黎捡获一年级学生秦朗的寒假作业”短视频的评论区假冒“秦朗舅舅”进行引流并进行造谣、摆拍、直播，引发“全网寻人”行动。目前，江苏南通公安机关已对杨某处以行政处罚，其账号已关停。</p>\n<p>人民数据研究院研究员侯鑫淼表示，个别网络主播为了引流吸粉进行摆拍，编造虚假事件，这会造成不明真相的粉丝效仿，进而扰乱公共秩序，给社会带来极大危害。</p>\n<p>治安管理处罚法第二十五条提到，散布谣言，谎报险情、疫情、警情或者以其他方法故意扰乱公共秩序的，处五日以上十日以下拘留，可以并处五百元以下罚款；情节较轻的，处五日以下拘留或者五百元以下罚款。</p>\n<p>“网络不是法外之地，希望广大网民朋友擦亮眼睛、理性思考，不造谣、不信谣、不传谣，共建清朗网络家园。”侯鑫淼说。</p>\n');
INSERT INTO `article_body` VALUES (1784224045589434369, '人民网广州4月1日电 （周睿、朴馨语）4月1日，国务院新闻办公室举行第135届中国进出口商品交易会（以下简称“广交会”）新闻发布会。会上，商务部国际贸易谈判代表兼副部长王受文介绍，本届广交会将于4月15日至5月5日在广州举办，线上平台继续常态化全年运营。\n\n据了解，本届广交会展览面积是155万平方米，有2.86万家企业参加出口展，其中新参展的企业超过4300家。有680家企业参加广交会的进口展。现在初步统计，已有来自215个国家和地区的9.3万采购商完成预注册，220多家头部企业和工商机构确认组织代表团来参加本届广交会。“这些数字都超过了往届的同期规模。”王受文表示，目前，广交会各项筹备工作进展顺利。\n\n提前注册办证、现场24小时办证、多地设置办证组…“广交会有20万境外客人参展，我们要保证他们能够凭证件及时进入交易场所，所以我们提高参展证件的办理效率。”王受文介绍，通过办证效率的提高，预计在高峰时期办证等待时间将从1.5小时缩短到30分钟。此外，提升广交会参展采购商的签证便利程度，为境外采购商提供“绿色通道”，现在90%的驻外使领馆为参加广交会的客人办理签证的时间控制在4个工作日之内。\n\n“为满足企业需要，第135届广交会将举办超过600场市场化的贸易促进活动，数量和种类均创历史新高。”中国对外贸易中心主任储士家介绍，本届广交会贸易促进活动将突出供采对接、首发首展首秀、设计引领、贸易服务、行业交流等特点，帮助参展企业抓订单、创渠道、拓市场。', '<p>人民网广州4月1日电 （周睿、朴馨语）4月1日，国务院新闻办公室举行第135届中国进出口商品交易会（以下简称“广交会”）新闻发布会。会上，商务部国际贸易谈判代表兼副部长王受文介绍，本届广交会将于4月15日至5月5日在广州举办，线上平台继续常态化全年运营。</p>\n<p>据了解，本届广交会展览面积是155万平方米，有2.86万家企业参加出口展，其中新参展的企业超过4300家。有680家企业参加广交会的进口展。现在初步统计，已有来自215个国家和地区的9.3万采购商完成预注册，220多家头部企业和工商机构确认组织代表团来参加本届广交会。“这些数字都超过了往届的同期规模。”王受文表示，目前，广交会各项筹备工作进展顺利。</p>\n<p>提前注册办证、现场24小时办证、多地设置办证组…“广交会有20万境外客人参展，我们要保证他们能够凭证件及时进入交易场所，所以我们提高参展证件的办理效率。”王受文介绍，通过办证效率的提高，预计在高峰时期办证等待时间将从1.5小时缩短到30分钟。此外，提升广交会参展采购商的签证便利程度，为境外采购商提供“绿色通道”，现在90%的驻外使领馆为参加广交会的客人办理签证的时间控制在4个工作日之内。</p>\n<p>“为满足企业需要，第135届广交会将举办超过600场市场化的贸易促进活动，数量和种类均创历史新高。”中国对外贸易中心主任储士家介绍，本届广交会贸易促进活动将突出供采对接、首发首展首秀、设计引领、贸易服务、行业交流等特点，帮助参展企业抓订单、创渠道、拓市场。</p>\n');
INSERT INTO `article_body` VALUES (1784224266474065922, '新华社北京4月7日电　国务院总理李强4月7日上午在北京人民大会堂会见美国财政部长耶伦。\n\n李强表示，在两国元首战略指引下，当前中美关系出现企稳态势。习近平主席不久前同拜登总统通电话，双方一致同意要加强对话、管控分歧、推进合作，推动中美关系稳定发展。中方希望中美做伙伴而不是对手，相互尊重、和平共处、合作共赢。希望美方同中方更多相向而行，继续落实好两国元首重要共识，让“旧金山愿景”成为“实景”。\n\n李强指出，中美作为世界前两大经济体，经济利益深度交融，加强经贸合作对双方各自发展和全球经济增长都具有重要意义。双方要加强沟通，共同找到管控分歧、解决分歧的办法，让中美经贸合作稳定、顺畅、高效，为两国企业和人民创造更多实惠，也为世界经济发展和民生改善作出贡献。希望美方与中方一道，坚持公平竞争、开放合作的市场经济基本准则，不把经贸问题泛政治化、泛安全化。要以市场眼光和全球视野，从经济规律出发，客观、辩证看待产能问题。中国的新能源产业发展，将对全球绿色低碳转型作出重要贡献。中方愿同美方在应对气候变化等问题上加强政策协调，共同应对全球性挑战。\n\n耶伦表示，在双方共同努力下，美中关系变得更加稳定。作为全球前两大经济体，美中双方应当负责任地管理双边经济关系。美方赞赏美中经济对话合作取得的进展，不寻求同中方“脱钩”，愿同中方一道落实两国元首旧金山会晤达成的重要共识，坦诚沟通，避免误解，深化交流合作，妥善管控分歧，共同应对紧迫的全球性挑战，推动美中关系稳定发展。', '<p>新华社北京4月7日电　国务院总理李强4月7日上午在北京人民大会堂会见美国财政部长耶伦。</p>\n<p>李强表示，在两国元首战略指引下，当前中美关系出现企稳态势。习近平主席不久前同拜登总统通电话，双方一致同意要加强对话、管控分歧、推进合作，推动中美关系稳定发展。中方希望中美做伙伴而不是对手，相互尊重、和平共处、合作共赢。希望美方同中方更多相向而行，继续落实好两国元首重要共识，让“旧金山愿景”成为“实景”。</p>\n<p>李强指出，中美作为世界前两大经济体，经济利益深度交融，加强经贸合作对双方各自发展和全球经济增长都具有重要意义。双方要加强沟通，共同找到管控分歧、解决分歧的办法，让中美经贸合作稳定、顺畅、高效，为两国企业和人民创造更多实惠，也为世界经济发展和民生改善作出贡献。希望美方与中方一道，坚持公平竞争、开放合作的市场经济基本准则，不把经贸问题泛政治化、泛安全化。要以市场眼光和全球视野，从经济规律出发，客观、辩证看待产能问题。中国的新能源产业发展，将对全球绿色低碳转型作出重要贡献。中方愿同美方在应对气候变化等问题上加强政策协调，共同应对全球性挑战。</p>\n<p>耶伦表示，在双方共同努力下，美中关系变得更加稳定。作为全球前两大经济体，美中双方应当负责任地管理双边经济关系。美方赞赏美中经济对话合作取得的进展，不寻求同中方“脱钩”，愿同中方一道落实两国元首旧金山会晤达成的重要共识，坦诚沟通，避免误解，深化交流合作，妥善管控分歧，共同应对紧迫的全球性挑战，推动美中关系稳定发展。</p>\n');
INSERT INTO `article_body` VALUES (1784225211857604609, '# Spring简介\n## Spring概述\nSpring 是于 2003 年兴起的一个轻量级的 Java 开发框架，它是为了解决企业应用开发的复杂性而创建的。\nSpring是分层的 JavaSE/EE应用 fu11-statck轻量级开源框架，以I0C(Inverse of contro]控制反转)和A0P(Aspcet 0riented Programming 面向切面编程)为内核，提供了表现层 SpringMvc和持久层 spring JDBc以及业务层事物管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的 JavaEE 企业应用开源框架。\n## Spring优点\n1. 方便解耦，简化开发，通过 spring 提供的 IOC 容器，可以将对象间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。\n2. AOP 编程的支持通过 Spring的 AOP 功能，方便进行面向切面的编程，许多不容易用传统 00P 实现的功能可以通过AOP 轻松应付。\n3. 声明式事物的支持，可以将我们从单调烦闷的事物管理代码中解脱出来，通过声明式方式(配置)灵活的进行事务的管理，提高开发效率和质量\n4. 方便程序的测试可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。\n5. 方便集成各种优秀框架Spring可以降低各种框架的使用难度，提供了对各种优秀框架(struts、HibernateHessian、Quartz等)的直接支持，\n6. 降低 JavaEE API的使用难度，Spring对JavaEE API(如JDBC、 JavaMai1、远程调用等)进行了薄薄的封装层，使这些 API的使用难度大为降低。\n7. spring源码是经典学习范例Spring 的源代码设计精妙、结构清晰、匠心独运，处处体现着大师对 ]ava 设计模式灵活运用以及对Java 技术的高深造诣。它的源代码无疑是ava 技术的最佳实践的范例。', '<h1><a id=\"Spring_0\"></a>Spring简介</h1>\n<h2><a id=\"Spring_1\"></a>Spring概述</h2>\n<p>Spring 是于 2003 年兴起的一个轻量级的 Java 开发框架，它是为了解决企业应用开发的复杂性而创建的。<br />\nSpring是分层的 JavaSE/EE应用 fu11-statck轻量级开源框架，以I0C(Inverse of contro]控制反转)和A0P(Aspcet 0riented Programming 面向切面编程)为内核，提供了表现层 SpringMvc和持久层 spring JDBc以及业务层事物管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的 JavaEE 企业应用开源框架。</p>\n<h2><a id=\"Spring_4\"></a>Spring优点</h2>\n<ol>\n<li>方便解耦，简化开发，通过 spring 提供的 IOC 容器，可以将对象间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。</li>\n<li>AOP 编程的支持通过 Spring的 AOP 功能，方便进行面向切面的编程，许多不容易用传统 00P 实现的功能可以通过AOP 轻松应付。</li>\n<li>声明式事物的支持，可以将我们从单调烦闷的事物管理代码中解脱出来，通过声明式方式(配置)灵活的进行事务的管理，提高开发效率和质量</li>\n<li>方便程序的测试可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。</li>\n<li>方便集成各种优秀框架Spring可以降低各种框架的使用难度，提供了对各种优秀框架(struts、HibernateHessian、Quartz等)的直接支持，</li>\n<li>降低 JavaEE API的使用难度，Spring对JavaEE API(如JDBC、 JavaMai1、远程调用等)进行了薄薄的封装层，使这些 API的使用难度大为降低。</li>\n<li>spring源码是经典学习范例Spring 的源代码设计精妙、结构清晰、匠心独运，处处体现着大师对 ]ava 设计模式灵活运用以及对Java 技术的高深造诣。它的源代码无疑是ava 技术的最佳实践的范例。</li>\n</ol>\n');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `categoryname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '/category/front.png', '前端', NULL);
INSERT INTO `category` VALUES (2, '/category/back.png', '后端', NULL);
INSERT INTO `category` VALUES (3, '/category/database.png', '数据库', NULL);
INSERT INTO `category` VALUES (4, '/category/language.png', '编程语言', NULL);
INSERT INTO `category` VALUES (5, '/category/life.png', '生活', NULL);
INSERT INTO `category` VALUES (6, '/category/new.png', '新闻', NULL);
INSERT INTO `category` VALUES (7, '/category/play.png', '娱乐', NULL);
INSERT INTO `category` VALUES (8, '/category/sport.png', '运动', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `author_id` bigint(20) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `to_uid` bigint(20) NULL DEFAULT NULL,
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKecq0fuo9k0lnmea6r01vfhiok`(`article_id`) USING BTREE,
  INDEX `FKkvuyh6ih7dt1rfqhwsjomsa6i`(`author_id`) USING BTREE,
  INDEX `FKaecafrcorkhyyp1luffinsfqs`(`parent_id`) USING BTREE,
  INDEX `FK73dgr23lbs3ebex5qvqyku308`(`to_uid`) USING BTREE,
  CONSTRAINT `FK73dgr23lbs3ebex5qvqyku308` FOREIGN KEY (`to_uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKaecafrcorkhyyp1luffinsfqs` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKecq0fuo9k0lnmea6r01vfhiok` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkvuyh6ih7dt1rfqhwsjomsa6i` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT NULL,
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login` datetime NULL DEFAULT NULL,
  `mobile_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_awpog86ljqwb89aqa1c5gvdrd`(`account`) USING BTREE,
  UNIQUE INDEX `UK_ahtq5ew3v0kt1n7hf1sgp7p8l`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'shimh', b'1', '/user/admin.png', '2024-01-22 17:14:49', b'0', '919431514@qq.com', NULL, '18396816462', '史明辉', 'c237910910ffa1f4827bf7fe1831ce43', 'e4153a582cbc45c3a199998b506dab28', '0');
INSERT INTO `user` VALUES (15, 'shimh2', b'0', '/user/user_6.png', NULL, b'0', NULL, NULL, NULL, 'shimh2', '0df7246bbb5b1bf138edd17f7b64b33b', '480e1a68cbc7e05ff49f39d2b5222d0b', '0');
INSERT INTO `user` VALUES (1783891596569317377, 'lbj', b'0', NULL, '2024-04-27 09:11:14', b'0', NULL, NULL, NULL, NULL, '$2a$10$jrblDN64plrv53asE29SmuZhFSWQab90peH6ijgUR1fclkVlNoace', NULL, '0');
INSERT INTO `user` VALUES (1783892409089888257, 'test', b'0', NULL, '2024-04-27 09:14:28', b'0', NULL, NULL, NULL, NULL, '$2a$10$t.rryPrTn.aBrPJO5N1MKuE2EAn9COwiV9yX3eGZIby4AG5Pegp2W', NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
