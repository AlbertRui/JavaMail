Java实现邮箱验证
================

### JavaMail的简单概述:

JavaMail:顾名思义,提供给开发者处理电子邮件相关的编程接口,它是Sun发布的用来处理Email的API,它可以方便地执行一些常用的邮件传输,我们可以基于JavaMail开发出类似于Microsoft Outlook的应用程序,现在很多Web开发都需要使用JavaMail,如:

1.注册时发送激活邮件
2.用户生日时发送祝福邮件
3.软件发送给用户一些活动信息,等等........

### 邮件开发中的基本术语介绍:

1.电子邮箱:需要在邮箱服务器上进行申请的,相当于在服务器上给用户一个账号,然后给予对应的空间,使用这个空间进行发送和接受邮件

2.邮件服务器:要在Internet上提供电子邮件,必须有专门的电子邮件服务器,这些邮箱服务器相当于邮局,例如新浪,搜狐,网易等都有自己的邮件服务器

3.邮件收发协议(SMTP和POP3)的简单介绍:

SMTP(Simple Mail Transfer Protocol)即简单邮件传输协议,它是一组用于由源地址到目的地址传送邮件的规则,由它来控制信件的中转方式,SMTP协议属于TCP/IP协议簇,它帮助每台计算机在发送或中转信件时找到下一个目的地,通过SMTP协议所指定的服务器,就可以把E-mail寄到收信人的服务器上了,整个过程只要几分钟,SMTP服务器则是遵循SMTP协议的发送邮件服务器,用来发送或中转发出的电子邮件,其默认端口号为25

POP3:全名为"Post Office Protocol - Version 3"即"邮局协议版本3",是TCP/IP协议族中的一员,由RFC1939定义,本协议主要用于支持使用客户端远程管理在服务器上的电子邮件,提供了SSL加密的POP3协议被称为POP3S,其默认端口号为110

### 邮件接收和发送过程图解: 
![点击查看图解](https://github.com/AlbertRui/JavaMail/tree/master/WebRoot/images/mail.jpg)

### 邮件服务器的安装和配置:

1.下载和安装易邮邮件服务器 (https://github.com/AlbertRui/JavaMail/raw/master/software/eyoumailserversetup.exe)

2.安装完成之后打开软件在工具选项卡中选服务器设置进行域名的设置,如store.com

3.在账号选项卡中选新建账号表示在邮件服务器上添加邮件账号(其中账号名默认会添加@域名,如user@store.com)

### 邮件客户端软件的安装和配置:

常见的邮件客户端软件有outlook(微软的,收费)和foxmail(免费)

foxmail下载地址：https://github.com/AlbertRui/JavaMail/blob/master/software/FoxmailSetup_7.2.9.115.exe

在使用foxmail时需要填写邮箱账号信息(可以是网络邮箱(如QQ,新浪等)或在本地易邮邮箱服务器中的邮箱账号),在本地邮箱账号设置需注意:
