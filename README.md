# JavaCourse
2021年春季华中科技大学Java实验设计——内存倒排索引实现
## File Structure
```
SearchEngineForStudent
├─bin  // 生成的class字节码
│  └─production
│      └─SearchEngineForStudent
│          └─hust
│              └─cs
│                  └─javacourse
│                      └─search
│                          ├─index
│                          │  └─impl
│                          ├─parse
│                          │  └─impl
│                          ├─query
│                          │  └─impl
│                          ├─run
│                          └─util
├─index  // 保存倒排索引文件夹
├─javadoc  // 自动生成的javadoc文档
│  ├─hust
│  │  └─cs
│  │      └─javacourse
│  │          └─search
│  │              ├─index
│  │              │  ├─class-use
│  │              │  └─impl
│  │              │      └─class-use
│  │              ├─parse
│  │              │  ├─class-use
│  │              │  └─impl
│  │              │      └─class-use
│  │              ├─query
│  │              │  ├─class-use
│  │              │  └─impl
│  │              │      └─class-use
│  │              ├─run
│  │              │  └─class-use
│  │              └─util
│  │                  └─class-use
│  ├─index-files
│  ├─resources
│  └─script-dir
│      └─images
├─model  // 提供的UML图等
├─src    // 源代码
│  └─hust
│      └─cs
│          └─javacourse
│              └─search
│                  ├─index
│                  │  └─impl
│                  ├─parse
│                  │  └─impl
│                  ├─query
│                  │  └─impl
│                  ├─run
│                  └─util
└─text   // 待建立倒排索引的文本文件目录

```
## Usage
1. 建立倒排索引：首先将文本文件放于./text下，然后运行
```$xslt
>java -calsspath ./bin/production/SearchEngineForStudent hust.cs.javacourse.search.run.TestBuildIndex
```
2. 搜索
```$xslt
>java -classpath ./bin/production/SearchEngineForStudent hust.cs.javacourse.search.run.TestSearchIndex <Word1> [<Word2> <LogicalComb>]
```
其中Word1必须给出，后面为可选参数。当只给出一个搜索词的时候，程序进行单个检索词搜索，当给出了两个搜索词以及逻辑组合方式时，程序进行两个检索词搜索的功能。最终程序打印出搜索结果。