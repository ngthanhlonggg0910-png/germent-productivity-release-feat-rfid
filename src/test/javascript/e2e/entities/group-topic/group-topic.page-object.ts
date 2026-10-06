import { element, by, ElementFinder } from 'protractor';

export class GroupTopicComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-group-topic div table .btn-danger'));
  title = element.all(by.css('jhi-group-topic div h2#page-heading span')).first();

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

export class GroupTopicUpdatePage {
  pageTitle = element(by.id('jhi-group-topic-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  topicTypeSelect = element(by.id('field_topicType'));
  typeSelect = element(by.id('field_type'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setTopicTypeSelect(topicType) {
    await this.topicTypeSelect.sendKeys(topicType);
  }

  async getTopicTypeSelect() {
    return await this.topicTypeSelect.element(by.css('option:checked')).getText();
  }

  async topicTypeSelectLastOption() {
    await this.topicTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setTypeSelect(type) {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect() {
    return await this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption() {
    await this.typeSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

export class GroupTopicDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-groupTopic-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-groupTopic'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
