# Java实现邮箱验证
## JavaMail的简单概述:
* JavaMail是SUN提供给开发人员在应用程序中实现邮件发送和接收功能而提供的一套标准开发类库，支持常用的邮件协议，如SMTP、POP3、IMAP，开发人员使用JavaMail编写邮件程序时，无需考虑底层的通信细节(Socket)，JavaMail也提供了能够创建出各种复杂MIME格式的邮件内容的API。使用JavaMail，我们可以实现类似OutLook、FoxMail的软件。虽然JavaMail(仅支持JDK4及以上)也是Java的API之一，但是却没有直接加入到JDK中，所以我们需要另行下载。另外，JavaMail依赖JAF(JavaBeans Activation Framework)，JAF在Java6之后已经合并到JDK中.
* 我们可以基于JavaMail开发出类似于Microsoft Outlook的应用程序,现在很多Web开发都需要使用JavaMail,如:
    * 注册时发送激活邮件
    * 用户生日时发送祝福邮件
    * 软件发送给用户一些活动信息,等等........
* JavaMail包含两部分内容:
    * 一部分是JavaMail API，定义了一组平台无关、独立于通讯协议的邮件程序框架，该部分称为应用级接口，也就是供我们调用的部分.
    * 另一部分是service provider，该部分使用特定的协议语言来实现第一部分定义的抽象类和接口，这些协议包括：SMTP、NNTP、POP3、IMAP，如果让JavaMail与邮件服务器通信，就需要相应的协议支持，该部分称为服务提供者接口，也就是JavaMail自身需要的协议支持。在使用JavaMail时，通常我们只需将mail.jar放在classpath下使用，它包含了JavaMail API部分和SUN自己实现的service provider部分。可能也有特殊的时候，我们应用程序中需要自己实现service provider部分，那我们只需要mailapi.jar.
* 下面通过几个类来简单认识下JavaMail API：
    * javax.mail.Session：上下文环境信息，如服务器的主机名、端口号、协议名称等  
	* javax.mail.Message：邮件模型，发送邮件和接收邮件的媒介，封装了邮件的信息，如发件人、收件人、邮件标题、邮件内容等  
    * javax.mail.Transport：连接邮件SMTP服务器，发送邮件  
    * javax.mail.Store：连接邮件POP3、IMAP服务器，收取邮件  
* 创建Session对象时可能需要的属性详细信息如下：
<HTML>
<body>
	<table>
		<tr>
			<th width=20%, bgcolor=yellow >name</th>
			<th width=8%, bgcolor=yellow>type</th>
			<th width="72%", bgcolor=yellow>Description</th>
		</tr>
		<tr>
			<td>mail.debug</td>
			<td>boolean</td>
			<td>The initial debug mode. Default is false.</td>
		</tr>
		<tr>
			<td>mail.from</td>
			<td>String</td>
			<td>The return email address of the current user, used by the <code>InternetAddress</code> method<code>getLocalAddress</code>.
			</td>
		</tr>
		<tr>
			<td>mail.mime.address.strict</td>
			<td>boolean</td>
			<td>The MimeMessage class uses the <code>InternetAddress</code> method <code>parseHeader</code> to parse headers in messages. This property controls the strict flag passed to the<code>parseHeader</code> method. The default is true.
			</td>
		</tr>
		<tr>
			<td>mail.host</td>
			<td>String</td>
			<td>The default host name of the mail server for both Stores and Transports. Used if the<code>
					mail.<em>protocol</em>.host
				</code> property isn't set.
			</td>
		</tr>
		<tr>
			<td>mail.store.protocol</td>
			<td>String</td>
			<td>Specifies the default message access protocol. The <code>Session</code> method<code>getStore()</code> returns a Store object that implements this protocol. By default the first Store provider in the configuration files is returned.
			</td>
		</tr>
		<tr>
			<td>mail.transport.protocol</td>
			<td>String</td>
			<td>Specifies the default message transport protocol. The <code>Session</code> method<code>getTransport()</code> returns a Transport object that implements this protocol. By default the first Transport provider in the configuration files is returned.
			</td>
		</tr>
		<tr>
			<td>mail.user</td>
			<td>String</td>
			<td>The default user name to use when connecting to the mail server. Used if the <code>
					mail.<em>protocol</em>.user
				</code> property isn't set.
			</td>
		</tr>
		<tr>
			<td>mail.<em>protocol</em>.class
			</td>
			<td>String</td>
			<td>Specifies the fully qualified class name of the provider for the specified protocol. Used in cases where more than one provider for a given protocol exists; this property can be used to specify which provider to use by default. The provider must still be listed in a configuration file.</td>
		</tr>
		<tr>
			<td>mail.<em>protocol</em>.host
			</td>
			<td>String</td>
			<td>The host name of the mail server for the specified protocol. Overrides the <code>mail.host</code> property.
			</td>
		</tr>
		<tr>
			<td>mail.<em>protocol</em>.port
			</td>
			<td>int</td>
			<td>The port number of the mail server for the specified protocol. If not specified the protocol's default port number is used.</td>
		</tr>
		<tr>
			<td>mail.<em>protocol</em>.user
			</td>
			<td>String</td>
			<td>The user name to use when connecting to mail servers using the specified protocol. Overrides the<code>mail.user</code> property.&nbsp;
			</td>
		</tr>
	</table>
</body>
</HTML>

## 邮件开发中的基本术语介绍:

* 电子邮箱:需要在邮箱服务器上进行申请的,相当于在服务器上给用户一个账号,然后给予对应的空间,使用这个空间进行发送和接受邮件
* 邮件服务器:要在Internet上提供电子邮件,必须有专门的电子邮件服务器,这些邮箱服务器相当于邮局,例如新浪,搜狐,网易等都有自己的邮件服务器
* 邮件收发协议(SMTP和POP3)的简单介绍:
    * SMTP(Simple Mail Transfer Protocol)即简单邮件传输协议,它是一组用于由源地址到目的地址传送邮件的规则,由它来控制信件的中转方式,SMTP协议属于TCP/IP协议簇,它帮助每台计算机在发送或中转信件时找到下一个目的地,通过SMTP协议所指定的服务器,就可以把E-mail寄到收信人的服务器上了,整个过程只要几分钟,SMTP服务器则是遵循SMTP协议的发送邮件服务器,用来发送或中转发出的电子邮件,其默认端口号为25
    * POP3:全名为"Post Office Protocol - Version 3"即"邮局协议版本3",是TCP/IP协议族中的一员,由RFC1939定义,本协议主要用于支持使用客户端远程管理在服务器上的电子邮件,提供了SSL加密的POP3协议被称为POP3S,其默认端口号为110

## 邮件接收和发送过程图解:

![邮件接收和发送过程图解](/WebRoot/images/mail.jpg)

## 邮件服务器的安装和配置:

* 下载和安装易邮邮件服务器[点击下载](https://github.com/AlbertRui/JavaMail/raw/master/software/eyoumailserversetup.exe)
* 安装完成之后打开软件在工具选项卡中选服务器设置进行域名的设置,如`store.com`
* 在账号选项卡中选新建账号表示在邮件服务器上添加邮件账号(其中账号名默认会添加@域名,如`user@store.com`)
## 邮件客户端软件的安装和配置:
* 常见的邮件客户端软件有outlook(微软的,收费)和foxmail(免费)
* foxmail下载地址：[点击下载](https://github.com/AlbertRui/JavaMail/blob/master/software/FoxmailSetup_7.2.9.115.exe)
* 在使用foxmail时需要填写邮箱账号信息(可以是网络邮箱(如QQ,新浪等)或在本地易邮邮箱服务器中的邮箱账号),在本地邮箱账号设置需注意:邮件服务器(发送和接收)都为localhost
