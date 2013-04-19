package com.laoer.bbscs.comm;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class FilterDirector {

  /**
   * Reference to Builder currently used
   */
  private FilterBuilder builder;

  /**
   * Create a new <code>FilterDirector</code> instance.
   *
   * @param builder the builder which will create the product
   */
  public FilterDirector(FilterBuilder builder) {
    this.builder = builder;
  }

  /**
   * Construct the Product using the Builder.
   */
  public void construct() {
    builder.buildFilter();
  }

}
