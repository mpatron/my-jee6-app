package org.jobjects.orm.discussion;

import java.util.logging.Logger;

import org.testng.annotations.Test;

public class DiscussionFacadeTest {
  private static Logger log = Logger.getLogger(DiscussionFacadeTest.class
      .getName());

  @Test(groups = "MaSuite")
  public void f() {
    Discussion instance = new Discussion();
    instance.getId();
    log.info("Test pipo sur DiscussionFacade");
  }
}
