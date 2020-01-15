package com.gemicle.eventing.kovtun.eventhandler;

/**
 * Class description goes here.
 *
 * @version 1.0.0 20.12.2019
 * @author Kovtun Bogdan
 */

public interface AutoRegisteredEventHandler<T> {

	/**
	 * for subsctibing you should add @Subscribe annotation to this method and
	 * add @Component annotation to class when you implement this interface
	 */
	public void process(T data);
}
