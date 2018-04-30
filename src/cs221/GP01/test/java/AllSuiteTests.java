/*
   * @(#) AllSuieTests.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)

@SelectPackages({"cs221.GP01.test.java.backend","cs221.GP01.test.java.ui"})

public class AllSuiteTests {

}
