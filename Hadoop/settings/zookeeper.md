
# 설정

```bash
# Zookeeper
export ZOOKEEPER_HOME=/home/ubuntu/zookeeper
export PATH=$PATH:$ZOOKEEPER_HOME/bin
```

```bash
tickTime=2000
dataDir=/usr/local/lib/zookeeper/data
clientPort=2181
initLimit=5
syncLimit=2

admin.serverPort=12121
```