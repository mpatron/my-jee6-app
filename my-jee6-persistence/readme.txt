https://github.com/mcgivrer/jpaunittest/wiki
http://www.mastertheboss.com/maven-hibernate-jpa/maven-and-jpa-tutorial


  <repositories>
    <repository>
      <releases>
        <enabled>true</enabled>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
        <updatePolicy>never</updatePolicy>
      </snapshots>
      <id>jboss-public-repository-group</id>
      <name>JBoss Public Maven Repository Group</name>
      <url>https://repository.jboss.org/nexus/content/groups/public/</url>
    </repository>
    <repository>
      <releases>
        <enabled>true</enabled>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
        <updatePolicy>never</updatePolicy>
      </snapshots>
      <id>jboss-public-repository-group-developer</id>
      <name>JBoss Public Maven Repository Group Developer</name>
      <url>https://repository.jboss.org/nexus/content/groups/developer/</url>
    </repository>
    <repository>
      <id>oss.sonatype.org</id>
      <name>OSS Sonatype Staging</name>
      <url>https://oss.sonatype.org/content/groups/staging</url>
    </repository>
    <repository>
      <id>glassfish-repository</id>
      <name>Java.net Repository for Glassfish</name>
      <url>http://download.java.net/maven/glassfish</url>
    </repository>
    <repository>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>central</id>
      <name>Central Repository</name>
      <url>http://repo.maven.apache.org/maven2</url>
    </repository>
  </repositories>
  <pluginRepositories>
    <pluginRepository>
      <releases>
        <enabled>true</enabled>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
        <updatePolicy>never</updatePolicy>
      </snapshots>
      <id>jboss-public-repository-group</id>
      <name>JBoss Public Maven Repository Group</name>
      <url>https://repository.jboss.org/nexus/content/groups/public/</url>
    </pluginRepository>
    <pluginRepository>
      <releases>
        <enabled>true</enabled>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
        <updatePolicy>never</updatePolicy>
      </snapshots>
      <id>jboss-public-repository-group-developer</id>
      <name>JBoss Public Maven Repository Group Developer</name>
      <url>https://repository.jboss.org/nexus/content/groups/developer/</url>
    </pluginRepository>
    <pluginRepository>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>central</id>
      <name>Central Repository</name>
      <url>http://repo.maven.apache.org/maven2</url>
    </pluginRepository>
  </pluginRepositories>
  