# jladmin
多模块springboot单体架构,隔壁adminbackend项目的多模块化
模块如下：

## jladmin-common：
通用模块，主要负责一些公共父类和配置，BaseEntity，Result，ResultStatusCode等。所有模块都需要先引用这个模块

## jladmin-generator：
代码生成模块，参考mp的代码生成改造而成，需要输入模块名，目录名，和表名

## jladmin-system：
主模块，也是入口模块，需要引入所有其他模块（可以不引入generator），包括所有系统配置和系统所需的基础服务，非常重要的模块。

## jladmin-manage：
业务模块，主要负责客制化管理业务，这里我写了资讯的管理例子。
