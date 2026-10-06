import { element, by, ElementFinder } from 'protractor';

export class SysRoleHasFunctionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sys-role-has-function div table .btn-danger'));
  title = element.all(by.css('jhi-sys-role-has-function div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class SysRoleHasFunctionUpdatePage {
  pageTitle = element(by.id('jhi-sys-role-has-function-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  activatedInput = element(by.id('field_activated'));
  sysFunctionSelect = element(by.id('field_sysFunction'));
  authoritySelect = element(by.id('field_authority'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  getActivatedInput() {
    return this.activatedInput;
  }

  async sysFunctionSelectLastOption() {
    await this.sysFunctionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sysFunctionSelectOption(option) {
    await this.sysFunctionSelect.sendKeys(option);
  }

  getSysFunctionSelect(): ElementFinder {
    return this.sysFunctionSelect;
  }

  async getSysFunctionSelectedOption() {
    return await this.sysFunctionSelect.element(by.css('option:checked')).getText();
  }

  async authoritySelectLastOption() {
    await this.authoritySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async authoritySelectOption(option) {
    await this.authoritySelect.sendKeys(option);
  }

  getAuthoritySelect(): ElementFinder {
    return this.authoritySelect;
  }

  async getAuthoritySelectedOption() {
    return await this.authoritySelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class SysRoleHasFunctionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sysRoleHasFunction-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sysRoleHasFunction'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
