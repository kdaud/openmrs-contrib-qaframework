/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ManagePrivilegesPage extends Page {

	private static final String PAGE_URL = "adminui/metadata/privileges/managePrivileges.page";
	private static final By ADD_NEW_PRIVILEGE_BUTTON = By.cssSelector("#content > input");
	private static final By EDIT_PRIVILEGE_ICON = By.cssSelector("i.icon-pencil.edit-action");
	private static final By DELETE_ICON = By.cssSelector("i.icon-trash.delete-action.right");
	private static final By SEARCH_FIELD = By.cssSelector("#list-privileges_filter label input[type=text]");
	private static final By CONFIRM_BUTTON = By.cssSelector("#purgePrivilegeForm button.confirm.right");
	
	public ManagePrivilegesPage(Page parent) {
		super(parent);
	}
	
	public AddEditNewPrivilegePage goToAddNewPrivilegePage() {
		clickOn(ADD_NEW_PRIVILEGE_BUTTON);
		return new AddEditNewPrivilegePage(this);
	}
	
	public void confirmPrivilegeDelete() {
		clickOn(CONFIRM_BUTTON);
	}
	
	public void goToEditPrivilegePage() {
		clickOn(EDIT_PRIVILEGE_ICON);
	}
	
	public void searchForPrivilege(String searchInput) {
		setText(SEARCH_FIELD, searchInput);
	}
	
	public void deletePrivilege() {
		clickOn(DELETE_ICON);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
