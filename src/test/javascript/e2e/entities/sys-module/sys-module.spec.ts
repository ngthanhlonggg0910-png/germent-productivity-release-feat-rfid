import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SysModuleComponentsPage, SysModuleDeleteDialog, SysModuleUpdatePage } from './sys-module.page-object';

const expect = chai.expect;

describe('SysModule e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysModuleComponentsPage: SysModuleComponentsPage;
  let sysModuleUpdatePage: SysModuleUpdatePage;
  let sysModuleDeleteDialog: SysModuleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysModules', async () => {
    await navBarPage.goToEntity('sys-module');
    sysModuleComponentsPage = new SysModuleComponentsPage();
    await browser.wait(ec.visibilityOf(sysModuleComponentsPage.title), 5000);
    expect(await sysModuleComponentsPage.getTitle()).to.eq('ctApp.sysModule.home.title');
  });

  it('should load create SysModule page', async () => {
    await sysModuleComponentsPage.clickOnCreateButton();
    sysModuleUpdatePage = new SysModuleUpdatePage();
    expect(await sysModuleUpdatePage.getPageTitle()).to.eq('ctApp.sysModule.home.createOrEditLabel');
    await sysModuleUpdatePage.cancel();
  });

  it('should create and save SysModules', async () => {
    const nbButtonsBeforeCreate = await sysModuleComponentsPage.countDeleteButtons();

    await sysModuleComponentsPage.clickOnCreateButton();
    await promise.all([
      sysModuleUpdatePage.setNameInput('name'),
      sysModuleUpdatePage.setCaptionInput('caption'),
      sysModuleUpdatePage.setImageUriInput('imageUri'),
      sysModuleUpdatePage.sysGroupModuleSelectLastOption()
    ]);
    expect(await sysModuleUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await sysModuleUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');
    expect(await sysModuleUpdatePage.getImageUriInput()).to.eq('imageUri', 'Expected ImageUri value to be equals to imageUri');
    await sysModuleUpdatePage.save();
    expect(await sysModuleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysModuleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SysModule', async () => {
    const nbButtonsBeforeDelete = await sysModuleComponentsPage.countDeleteButtons();
    await sysModuleComponentsPage.clickOnLastDeleteButton();

    sysModuleDeleteDialog = new SysModuleDeleteDialog();
    expect(await sysModuleDeleteDialog.getDialogTitle()).to.eq('ctApp.sysModule.delete.question');
    await sysModuleDeleteDialog.clickOnConfirmButton();

    expect(await sysModuleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
