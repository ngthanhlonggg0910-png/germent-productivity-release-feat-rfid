import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GroupTopicComponentsPage, GroupTopicDeleteDialog, GroupTopicUpdatePage } from './group-topic.page-object';

const expect = chai.expect;

describe('GroupTopic e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupTopicComponentsPage: GroupTopicComponentsPage;
  let groupTopicUpdatePage: GroupTopicUpdatePage;
  let groupTopicDeleteDialog: GroupTopicDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GroupTopics', async () => {
    await navBarPage.goToEntity('group-topic');
    groupTopicComponentsPage = new GroupTopicComponentsPage();
    await browser.wait(ec.visibilityOf(groupTopicComponentsPage.title), 5000);
    expect(await groupTopicComponentsPage.getTitle()).to.eq('ctApp.groupTopic.home.title');
  });

  it('should load create GroupTopic page', async () => {
    await groupTopicComponentsPage.clickOnCreateButton();
    groupTopicUpdatePage = new GroupTopicUpdatePage();
    expect(await groupTopicUpdatePage.getPageTitle()).to.eq('ctApp.groupTopic.home.createOrEditLabel');
    await groupTopicUpdatePage.cancel();
  });

  it('should create and save GroupTopics', async () => {
    const nbButtonsBeforeCreate = await groupTopicComponentsPage.countDeleteButtons();

    await groupTopicComponentsPage.clickOnCreateButton();
    await promise.all([
      groupTopicUpdatePage.setNameInput('name'),
      groupTopicUpdatePage.topicTypeSelectLastOption(),
      groupTopicUpdatePage.typeSelectLastOption()
    ]);
    expect(await groupTopicUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await groupTopicUpdatePage.save();
    expect(await groupTopicUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await groupTopicComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GroupTopic', async () => {
    const nbButtonsBeforeDelete = await groupTopicComponentsPage.countDeleteButtons();
    await groupTopicComponentsPage.clickOnLastDeleteButton();

    groupTopicDeleteDialog = new GroupTopicDeleteDialog();
    expect(await groupTopicDeleteDialog.getDialogTitle()).to.eq('ctApp.groupTopic.delete.question');
    await groupTopicDeleteDialog.clickOnConfirmButton();

    expect(await groupTopicComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
