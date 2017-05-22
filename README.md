# xframework
![servlet](https://github.com/huhuics/Accumulate/blob/master/image/xframework.jpg?raw=true)
 + `DispatcherServlet`的作用是将客户端的Request请求映射到相应的`Handler`。在框架初始化时将请求与`Handler`保存在`Map<Requester, Handler>`中，`Requester`对象由两个属性，分别是requestMethod(如GET, POST)和requestPath(如/account)，`Handler`中保存action的类和方法。这样在Map中就缓存了一个请求对应一个action的方法。
 + AOP模块的实现采用的是“链式代理”解决嵌套增强的问题。`链式代理`：将一个目标类的所有代理类存放在List中，通过递归调用，实现[前置增强1, 前置增强2... 目标方法, 后置增强1, 后置增强2...]
