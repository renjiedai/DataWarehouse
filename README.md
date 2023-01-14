# DataWarehouse

本项目为同济大学软件学院数据仓库课程项目，项目包含了ETL以及多种数据库的存储方式和查询对比。前端采用了常用的Vue框架，并调用后端接口进行数据获取和展示。后端分为两部分，关系型数据库和图数据库的后端使用Spring Boot框架，其中MySQL数据查询操作使用Spring Data JPA；对于分布式数据库，后端使用flask框架，并通过pyspark与hive进行连接查询。

## 不同存储方式的适宜查询分析

在本项目中，我们使用了关系型数据库、分布式数据库和图数据库，他们分别适用于不同的查询。

- **关系型数据库**，是指采用了[关系模型](https://baike.baidu.com/item/关系模型/3189329?fromModule=lemma_inlink)来组织数据的数据库，常常将同一业务范围内或同一方面的实体或事物组合成一张表，十分适合作单一条件的筛选查询，使用索引后的单表扫描效率十分高效。如根据某一类别的所有电影，某一时间段内的所有电影等。同时，适当使用索引也会加快多条件多表查询的速度。因此多条件的结果筛选同样适合关系型数据库，但是整体而言关系型数据库对于多表操作效率还是较低。
- **分布式数据库**，是指将位于不同地点的许多计算机通过网络互相连接，每台计算机都具有自己局部的数据库，进而共同组成一个逻辑上集中、物理上分布的大型数据库。分布式数据库适合于分布式的数据管理，在拥有多节点的情况下可以确保系统的可靠性。同时，由于可以依靠多个分布结点的算力，所以其在大数据量时的计算会更加快捷，比如不同条件的符合查询，当数据量越大时，分布式数据库所体现的优势越大。因此分布式数据库的优势主要在于大规模数据下的高计算性能和可靠性。
- **图数据库**，是以点、边为基础存储单元，以高效存储、查询图数据为设计原理的数据管理系统。由于天然的就能够反映结点和结点之间的关系，通过建立结点关系网络来挖掘深层潜在的关系。在性能方面，图数据结构直接存储了节点之间的依赖关系，这使得其在关系查询上相比其他类型数据库有巨大性能优势。因此图数据库非常适合于做复杂关系之间的查询。

综上：关系型数据库更适合**单表查询**，分布式数据库更适合**大数据查询**，图数据库更适合**复杂关系查询。**

例如在本项目中，MySQL的单表查询：如“XX年XX月有多少电影”、“XX电影共有多少版本”是查的最快的；而像“XX演员参演多少电影”、“用户评价中正面/负面评价占比XX%以上的电影有哪些”这些涉及数据量较大的查询，往往是Hive最快；一些复杂关系查询，如“合作次数超过XX的导演和演员有哪些”、“如果要拍一部XXX类型的电影，最受关注（评论最多）的演员组合（2人，3人等）是什么”，Neo4j的查询速度则是最快的。这些比较结果进一步验证了上面的推论。

## 数据库存储优化      

### 关系型数据库（MySQL）

#### **一.数据类型优化** 

本优化具体到各个数据表中各属性数据类型的优化，减少数据表中各字段所在列的存储空间，提高查询速度：

1. 用 SMALLINT、TINYINT 作为整数类型而非 INT，INTEGER
2. 字符串不使用STRING，使用的VARCHAR 长度只分配比最小需求空间稍大的空间 ，如VARCHAR（255）

3. 外键之外的属性避免使用NULL字段，避免影响索引查询。

4. 删去查询中不会使用的字段，减少列数，提高扫描速度 。

#### **二.冗余存储优化**

本优化主要为设置冗余字段，具体如下：

我们的MySQL后端采用了Spring Boot的框架，其进行原生SQL查询十分不便，因此我们会尽量将查询转变为jpa单表查询，在此背景下，我们在movie表中已经设置了time_id和format_id这两个外键的情况下又存储了release_time、movie_format_num这两个冗余字段，一定程度上提高了后端接口查询的速度。

对比如下：

查询XX年XX月XX日-YY年YY月YY日新增多少电影：

建立冗余字段前：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmY2ZGM4NDI0MzBhN2FiMmY0ZTY1NjQ3NTBmOTNhZDJfTGdSUnBZVGFINEwyaVQySkJ4TVV4TzdpdDd0d2x6elVfVG9rZW46Ym94Y25jNll2VXVyUEM0czBwY0s3U1p6enZoXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

建立冗余字段后：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=MDhhOGU1MGQ0MjJiYmRhMWU3MDE4MWFmY2Y5YTBhNjVfRnpnZWllWWY1Y3dXdkVlSm9FWlc3SGY1TVVIcHJIM3NfVG9rZW46Ym94Y25NZ2dUbUpVTGxmNENoaUREeWNqbHVjXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

以上为后端写好的接口在api-fox中测试的结果，不难看出设置冗余字段后后端接口返回时间大幅减少。

#### **三.索引建立优化**

建立索引有以下好处：

1.通过创建唯一性索引，可以保证数据库表中每一行数据的唯一性。

2.可以大大加快数据的检索速度，这也是创建索引的最主要的原因。

3.可以加速表和表之间的连接，特别是在实现数据的参考完整性方面特别有意义。

4.在使用分组和排序子句进行数据检索时，同时可以显著减少查询中分组和排序的时间。

于是根据查询条件进行有针对性地添加索引，从而提高查询速度，具体来说，主要是对于sql语句中on ,group by, order by 中出现的列使用索引。如下所示：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTY3OGI1NzBhOTRiMzhlNGYzNGY5ZTk0MjQyYWMwY2ZfTHNjbUpLSG5XS0FpdWNacnB6TUlNV3RGdjVQZjdsRFBfVG9rZW46Ym94Y25YeFBIT3JRcHVaZnVCNHl0SXZKd05nXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

添加索引前后对比如下：

查询用户评分XX分以上的电影有哪些：

添加movie_score索引前：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=Yjk5YzYwMmIzYWQyODUxMDVkODJlMTc2YzdhYmJhMzBfRjlWWVduQ2xyWDlURVh3QWZGNUR5dGJtRW0xREJRTW5fVG9rZW46Ym94Y25Xa0pxa0pGUnZjRTljMUIxSlo2c2pjXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

添加movie_score索引后：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=YzgxNzAwNmExMzI4MTEwOWUwMWViNmZjNjMzN2ViYjVfc1hUSDJtMUlsNjZXWmUzelRZSWpoZWhCY2RpOVR1YnVfVG9rZW46Ym94Y25nYWpYRnNGS05Kb2lYaVhCam80T1pjXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

不难看出添加索引后查询速率提高显著。

### 分布式存储（Hive与Hadoop集群）

#### 一.数据结构优化

我们注意到Hive与MySQL不同，它支持高级数据结构如map、array等，于是我们将导演、演员、主演等一对多字段用arrary进行存储，同时通过hive封装的高性能的array_contains方法进行相关SQL查询，大大降低了SQL语句的复杂程度，关于这一点我们可以用不支持高级数据结构的MySQL中的查询语句进行对比：

对于同一查询（XX年-XX年间XX演员主演的评分大于XX的电影有哪些），

MySQL的查询语句如下图所示：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=OGYzM2RhZTcxZTFlNzFlNDk3OWU4YWMzZjY0NGNkZDFfZTh4OW50dHpKS1ZTZ3RUREN4R3F4UW54aXYwd2JyZDZfVG9rZW46Ym94Y25JbkhPbUhVeVFndUFLdGhYRndoVEZmXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

需要join多个视图并且可以看出where后面的条件很复杂，因为同一电影的actor是分散存储的。

而我们用Hive进行查询时，SQL语句是这样的：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=MGZjNzJiN2VmMGMwMGZjNmUxZmY1ODIzMjIwYjJjM2FfYVQ1ZmJQTUlMNWVLZ1QwbFFuc283cFY1VW1meDJMV1NfVG9rZW46Ym94Y25zeG9nbmw2eW8yOHBNdjBrQmtsRWtiXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

不难看出，Hive的SQL查询语句语义更加清晰，结构更加简单，操作更加便捷，这些都要归功于array结构的设置

#### 二.存储结构优化

Hive的运行建立在分布式系统Hadoop之上，因此Hadoop本身的节点设置也是影响Hive查询的重要因素。我们使用的hadoop集群中默认是一个namenode以及一个datanode，然而这种情况下即使使用spark，进行计算的效率也比较有限，于是我们修改了该集群启动项的yml文件，将datanode数量增加为三个，实现了完全分布式系统，在一定程度上优化了文件的组织结构，提升了计算效率。

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ODIwZWVkMjc0ZTkwODdlOTYyYjAxMTAxMjhmOTFlNTlfTTZjbkdSdlRyWkU0OTlaQm9WT2dPRHZ3TGtZMTBtN3pfVG9rZW46Ym94Y25qd0MzUzVpSGUyUHpwZXdDNmNsTW5oXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=OWIxNDFhYmFkNmI5ZWIxMDI1YzRjM2RiZTU5MGRlYjBfTkNvWjhaa09CRjBXU2RaTGZTcGZjcDRudWdrejF5U0NfVG9rZW46Ym94Y25XOVFBc2J6UkdBQ3diZUxIOUJqTFpmXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

#### 三.冗余存储优化

由于hive不是关系型数据库，所以我们在设计hive表时将冗余存储做到了极致，依托于array结构，我们将电影的各维度信息都存入了一张表中，这大大简化了查询难度，对于关系型查询，我们本来也可以用一张表进行存储，但是考虑到后端flask也需要使用pyspark进行SQL查询，于是我们设置了三个冗余表分别存储导演与导演、演员与演员、导演与演员的信息，这个数据源来自于MySQL视图中的数据。冗余字段与冗余表的设置大大简化了后端sql部分的实现难度，我们还是与没有设置year、starring_actors等冗余字段的MySQL中的查询语句进行对比，如下所示：

查询XX年XX季度XX演员主演了多少电影：

MySQL:

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ODM2OGFhYjYxYTAwZWMxNTVlNjYzY2FiODUzODcxMjRfSnc3SGlRd2hzYVBnbGF6b2xOM0xDckJ4U25NdXRSQVJfVG9rZW46Ym94Y25wNXRCVURZRlVrRDN4UktZODVzanFYXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

Hive：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=NmU5MjhiNmNiYmUwNjljZWRlZDUyZGYxMTgzZmMxM2VfQWJhcWZCWXBWT2JZVU1QcjhTZFY4YUNScUtKWFRMWEdfVG9rZW46Ym94Y25JN0x0M2VZdjFGZGY1amZTeUxES1BjXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

可见后者省去了繁琐的各表之间的join，SQL语句写起来比MySQL简洁直观很多，大大方便了flask中关于pyspark-sql的书写。

#### 四.文件格式优化：

Hive的文件存储格式包括以下几类：TEXTFILE、SEQUENCEFILE、RCFILE、ORCFILE。其中TEXTFILE为默认格式，建表时不指定则默认为这个格式，导入数据时会直接把数据文件拷贝到hdfs上不进行处理。我们主要优化了部分较大数据源的存储格式（如movie表的数据源fact_movie），将其文件存储格式由TEXTFILE变为ORCFILE。

以下为优化前后对比结果：

TEXTFILE：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=MzlkNTNlZTNjYzNmNDc0NWFjMjVlYjNkYWNlMTAyYTVfd0JrRHdKVzM0a2hLcm9zZlRncUdHcUZNN1NqanJjMklfVG9rZW46Ym94Y25sTGlMQW0yMFkydnc1dElocVYxaHZmXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=MTU3NDYwMDNlODRlOThhZDMzODVmNzU3NjJlZDY0NjZfMlExaHpZNzhaYzNxUXkxN1VZMmxXcHBQVDBZdVJDRmxfVG9rZW46Ym94Y25ISXNrQmw4UkgzZ3BUQ3AxeVYzRmNiXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

ORCFILE：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWNiNjNiMjg3NTM2OWVkYWI4MzQ5ZTRiZjU0NDJiMDFfeVJBbXRUekVZZ1c3dExRNTF4SnozV050VkJkaGVLSXZfVG9rZW46Ym94Y25WcFFvVlBwYmtWZnNVcGRvVkJRMmViXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2Q4YjU0NmY0OGJmZGVmMDY2MTBkZmMxOWViZDA1ODlfcnlnZENMVGFsNnFKejZSVlpza21JckdIcEhsWkpqdEpfVG9rZW46Ym94Y25RMldCVktMaUl2WkIwaW9hN0dUenBnXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

不难看出，ORC格式的文件存储大小更小，而且查询速度也更加迅速。

### 图数据库（Neo4j）

#### 一.冗余信息优化

项目中为便于比较同时兼顾功能的完整性，也实现了neo4j对于节点信息的查询。此时可以通过增加信息冗余，以空间换时间，适当提升查询效率。即将电影的版本，评分等信息作为电影节点属性冗余存储，当需要根据电影名称查询演员或其他相关信息时，无需再通过关系去寻找目标节点，这时可直接通过查询电影节点属性获得相关值。

以评论数的query为例，优化效果见下：

优化前：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=NDJmNDVmZDA3N2NiOTljZWZhYTYyZmNjZTkwZDhjY2JfOHRva2tPaVF3c1g1MmhhZldDV3lqMERZU0dNRUdYTWVfVG9rZW46Ym94Y256RzlKUkdNZnYxRW95bHNVWFhUNmlEXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

优化后：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=YmJmOGUzMGQwMzUzZDVmMWJiYWM1MDI3ZTAwNTA2ZjlfajNJR2RsdUxReTQ1eVV2d25iRjl6eEhWbnhaSFJ2bzBfVG9rZW46Ym94Y25GT0lUMnBabjhRZU5TbFdKckljY3ZiXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

#### 二.服务器性能优化

由于项目中所用的neo4j是部署在云服务器上的，故系统的运行效率必然会受到服务器配置的影响，实际使用过程中，在条件允许的情况下，可以在neo4j.conf配置文件中将neo4j的默认配置进行修改，尽可能的为其分配更多的内存空间。

将swap分区由512M调整为8G后的优化效果：

优化前：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=OGVjYjM1ODU1MDZhZDQzYzBmZmY1YjJiMDU5Zjk2ZjdfSEVzb3g4WnRDZ1RrbE1TSWVha3YwSzhlUG1mNzJWeWlfVG9rZW46Ym94Y256Sm5yWHBGbG92WnRFcXgwMDlpbU9kXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

优化后：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=OTQzNzliN2M3NTQ5M2M2ZGQ3MjdmMjkwMWY1MjM2MzBfbFpNMU5lcmFxS0Y0OERuNDh5N3FVV2V4NWo3dFNmVVNfVG9rZW46Ym94Y25JdFVtTXozaWMzaUMwZnNOZnluR0NiXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

## 数据质量

本次项目中的数据经过了从评论集中获取电影编号、爬取信息到清洗数据、整合数据、存储等整个流程，这些过程对数据质量有一定的改善，但是同时由于数据来源复杂以及Amazon网站本身是为了营销数据格式不一等问题，数据质量仍然有可以改进的地方。

### 影响数据质量的因素：

#### **数据收集环节：**

- **数据源**：在数据收集过程中，数据源会影响大数据质量的真实性、完整性、一致性、准确性和安全性。数据源的安全运行、防止恶意攻击与篡改是保障大数据真实性、准确性和安全性质量的重要条件。同时，数据源运行的稳定性、 无间断性是保障大数据完整性的重要条件。所以虚假的、安全性差、不稳定不持续的数据源会严重影响数据质量。
- **数据收集方式**：数据的实时收集方式可有效保障大数据的时效性质量， 确保大数据分析与预测结果的时效性和价值性。对于Web 数据，多采用网络爬虫方式进行收集，所以爬虫时的防反爬机制不过关、爬虫效率低、时效性差也会影响源数据质量。
- **数据收集技术**：常用的收集软件有 Splunk、Sqoop、Flume、Logstash、Kettle 以及各种网络爬虫，如 Heritrix、Nutch 等。采用不过关的、低效率收集技术也势必会影响数据原始质量。

#### **数据预处理与存储环节：**

- **数据预处理**：大数据采集过程中通常有一个或多个数据源， 这些数据源包括同构或异构的数据库、文件系统、服务接口等，易受到噪声数据、数据值缺失、数据冲突等影响。
- **数据存储**：在大数据存储中，分布式存储与访问是其关键技术。分布式存储技术与数据存储介质的类型和数据的组织管理形式直接相关，所以一个内存容量低、组织管理形式低效的分布式框架也会成为制约数据质量的关键因素。

#### **数据处理与分析环节：**

- **数据处理**：数据处理系统的性能与优劣直接影响大数据质量的价值性、可用性、时效性和准确性，比如MapReduce与Spark相比就显得笨重、性能差，所以一个性能差的数据处理框架也会影响数据质量。
- **数据分析**：在数据分析环节，应根据大数据应用情境与决策需求，选择合适的数据分析技术，提高大数据分析结果的可用性、价值性和准确性质量。

### 我们为保证数据质量所做的工作：

- **数据完整性**：在数据extract过程中，爬取网页时亚马逊页面会有两种显示页面，在爬取的初始阶段会是一般的商品页面，但当爬取过程进行一段时间后，亚马逊会返回另一种页面，这种页面和前一种页面不同，在一般的正常访问中不会显示。这两种页面显示的内容有所不同，因此我们在数据爬取的过程中选取了两种页面信息的交集部分，来提高数据的完整性和可信度。
- **数据规范性**：由于Amazon电影数据设置了反爬等措施，并且数据本身展示上是为了销售，并不是为了分析数据，因此提取出的属性字段的规范性并不好，尤其体现在电影数据的电影标题、电影演员与导演等字符属性中。此外，电影数据的类别标签分类缺乏合理，不同电影的分类粒度不均匀，并且同一种类电影的标签数据表达形式不唯一，缺乏规范性。针对这种情况，我们通过提取一般规律来提高数据质量，如去除电影名称中括号内的内容与括号后的内容来提高电影名称的质量。另一方面，演员、导演等信息是通过字符串拼接得到的整体信息，由于字符串中混杂着换行符、前后空格，该种情况不利于对建立电影与人员的关系与搜索，因此对字符串进行切分与重组达到规范字符串的目的。
- **数据合理性**：这里的合理性主要是针对于电影的符号和标签等，Amazon电影网站中有些较老的网站，其中存在着很多不合理的符号表达，标签分类也有时候嵌套不合理。本项目中对电影数据进行规范处理，剔除无用的符号表达，以及尽量去除不合理的符号，使信息在逻辑层面趋于规范。
- **数据组织性**：在我们的分布式文件系统中，我们将建表数据（csv）存储在hadoop集群中的hdfs文件管理系统，同时建立了三个datanode来存储数据副本，这些datanode由一个namenode管理，namenode里存储文件名、文件路径、datanode数量、占用空间等**元数据。**通过这样的组织管理形式，我们的数据得到了高效的组织管理。

### 可改进的地方：

- 在数据transform的过程中，有许多的数据并不是非常完整，比如没有上映时间，没有版本信息，版本分类比较随意等等。对于上映时间，如果没有，则选取最早的一条评论的时间作为上映时间。对于商品的Title处理不太完整,可能会带有商品版本信息，没有将电影名完全抽取出来。并且在处理Amazon类别信息时，有一个难点在于如何结合电影类别的词意融合类别信息，对类别标签表达进行唯一化处理。
- 由于第二种亚马逊页面的限制，爬取到的演员和上映时间数据偏少，可以考虑引入第三方数据源。在筛选电影数据中，我们选择了豆瓣网站能够查询到的商品，可能会疏漏一些电影。

## 数据血缘

数据的产生、加工融合、流转流通，到最终消亡，数据之间自然会形成一种关系。我们借鉴人类社会中类似的一种关系来表达数据之间的这种关系，称之为数据的血缘关系。与人类社会中的血缘关系不同，数据的血缘关系还包含了一些特有的特征：

- **归属性**：一般来说，特定的数据归属特定的组织或者个人，数据具有归属性。
- **多源性**：同一个数据可以有多个来源（多个父亲）。一个数据可以是多个数据经过加工而生成的，而且这种加工过程可以是多个。
- **可追溯性**：数据的血缘关系，体现了数据的生命周期，体现了数据从产生到消亡的整个过程，具备可追溯性。
- **层次性**：数据的血缘关系是有层次的。对数据的分类、归纳、总结等对数据进行的描述信息又形成了新的数据，不同程度的描述信息形成了数据的层次。

### 数据血缘的使用场景列举如下：

#### **1.数据溯源：**

依托于数据血缘的可塑性特点，根据血缘中的数据链路关系，可实现指定数据的来源、去向的追溯，可帮助用户理解数据含义、在全流程上定位数据问题、进行数据关联影响分析等，解决多层复杂逻辑处理后的数据难以理解、难以应用、出现问题难以定位的问题。

#### 2.数据价值评估：

数据血缘则提供了一种基于数据实际应用的价值评估方法：使用者越多（需求方）、使用量级越大、更新越频繁的数据往往更有价值。

- **数据受众**：在血缘关系图上，右边的数据流出节点表示受众，亦即数据需求方，数据需求方越多表示数据价值越大；
- **数据更新量级**：数据血缘关系图中，数据流转线路的线条越粗，表示数据更新的量级越大，从一定程度上反映了数据价值的大小；
- **数据更新频次**：数据更新越频繁，表示数据越鲜活，价值越高。在血缘关系图上，数据流转线路的线段越短，更新越频繁。

#### **3.数据质量评估：**

数据血缘清晰的记录了数据来源以及数据流转过程中的处理方式和处理规则，能实现对各个数据节点的分析和数据质量评估。

#### 4.数据归档参考：

数据血缘中记录了数据的去向，可清晰的掌握数据被消费的情况，一旦数据没有消费者，那也就意味着数据已经失去价值。此时，可以对数据进行进一步评估，考虑进行归档或销毁处理。

### 本项目中数据血缘的使用：

#### 1.数据流向查询：

数据血缘在本项目中可用于追溯我们从基础数据来源——用户评论数据（movie.txt）到合并重复项之后的电影数据（AfterMerge.csv）这个过程中间的数据流向信息，包括每个阶段的数据来源和去向、数据量的直观变化等。

绘制桑基图如下：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=MTM1NDUwZmNkNmU4NzRmZjk5ZTE2YWE5MzZmMzhiYmRfYXlndHNnVHBlc2lkeGx5WXBZZ1RESGpTOGVYNHB0SUpfVG9rZW46Ym94Y243cHZyOTA3TG9wM0c1WmlWNE01VzVnXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

#### 2.各阶段数据量查询：

在ETL和数据清洗过程中，我们对各个阶段的数据量都做了统计，例如在爬虫程序中记录404网页、非电影页面的数量等，通过保存各阶段数据量，我们可以直观地了解数据清洗各阶段过程中对数据筛选的规模。

绘制桑基图如下：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWFjZWE5OWI2NzNjNGM4MGU1ZmM4NjA5YjNkZjI2NWZfcmZKTkRIN3B0QmU4cTBaRG53am5xZXhPY0F1V0tqSkNfVG9rZW46Ym94Y24ydHlkNXRuczZhRUlZR3JXSWRJaVRnXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

#### 3.数据溯源查询：

在本项目的爬虫亚马逊网站阶段中，我们发现亚马逊网页上的电影上映时间数据缺失过多，于是选择了第三方数据源——豆瓣网站进行了二次爬虫，这样做的结果是我们最终的合并数据中关于上映时间的来源分为了两部分，即Amazon网页和Douban网页，我们在爬虫过程中保存了各个上映时间对应的URL，通过这样一种数据血缘形式我们可以清晰的知道某条上映时间是来自Amazon还是Douban，以及直观上看两者占比大概是多少。

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmFiMTRhZDI3MTFiN2ZkMzFlYWQ4OTI3MmEyNGJkNzdfSGR5NFUwNUlRMTZjZ1JyUU9NOWFwUVJvQU5BdjdqMXpfVG9rZW46Ym94Y25PTXBsbmNObjdBQm1SMHZyRWtwc3ViXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)

#### 4.表数据流动查询：

为了便于springboot后端接口实现，我们在MySQL中提前做好了一部分SQL查询，将其保存为视图（view），方便后端利用jpa查询，以减少查询开支。于是在各个视图建立过程中就有了数据库表与视图（也可以看做一种表）之间的数据流动，通过马哈鱼数据血缘分析工具（SQLFlow），我们可以方便地监测到各个视图的各列数据来源，在后端发现视图中数据出错时能够及时定位数据库中源表相应字段，从而高效率的解决SQL出现的问题，重做SQL。以下是本项目的数据表的数据血缘图示：

![img](https://zat803lgtb.feishu.cn/space/api/box/stream/download/asynccode/?code=NDU5YjQwYjU4ZDkzODg1MjEzMDk4YTJmYTVlN2FmYjZfQmtFdjZhbVVvaWdkZk5oV001WWU3anVSOGRWeTRtZm9fVG9rZW46Ym94Y255ZDdzTUhHcmo5bXNZWVRjOElLWURlXzE2NzM2ODMyMzI6MTY3MzY4NjgzMl9WNA)