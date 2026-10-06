import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SysFunctionComponentsPage, SysFunctionDeleteDialog, SysFunctionUpdatePage } from './sys-function.page-object';

const expect = chai.expect;

describe('SysFunction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysFunctionComponentsPage: SysFunctionComponentsPage;
  let sysFunctionUpdatePage: SysFunctionUpdatePage;
  let sysFunctionDeleteDialog: SysFunctionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysFunctions', async () => {
    await navBarPage.goToEntity('sys-function');
    sysFunctionComponentsPage = new SysFunctionComponentsPage();
    await browser.wait(ec.visibilityOf(sysFunctionComponentsPage.title), 5000);
    expect(await sysFunctionComponentsPage.getTitle()).to.eq('ctApp.sysFunction.home.title');
  });

  it('should load create SysFunction page', async () => {
    await sysFunctionComponentsPage.clickOnCreateButton();
    sysFunctionUpdatePage = new SysFunctionUpdatePage();
    expect(await sysFunctionUpdatePage.getPageTitle()).to.eq('ctApp.sysFunction.home.createOrEditLabel');
    await sysFunctionUpdatePage.cancel();
  });

  it('should create and save SysFunctions', async () => {
    const nbButtonsBeforeCreate = await sysFunctionComponentsPage.countDeleteButtons();

    await sysFunctionComponentsPage.clickOnCreateButton();
    await promise.all([
      sysFunctionUpdatePage.setNameInput('name'),
      sysFunctionUpdatePage.setCaptionInput('caption'),
      sysFunctionUpdatePage.setLevelInput('5'),
      sysFunctionUpdatePage.setImageUriInput('imageUri'),
      sysFunctionUpdatePage.setEventNameInput('eventName'),
      sysFunctionUpdatePage.setHotkeyInput('hotkey'),
      sysFunctionUpdatePage.sysPermissionSelectLastOption(),
      sysFunctionUpdatePage.sysModuleSelectLastOption()
    ]);
    expect(await sysFunctionUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await sysFunctionUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');
    expect(await sysFunctionUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await sysFunctionUpdatePage.getImageUriInput()).to.eq('imageUri', 'Expected ImageUri value to be equals to imageUri');
    expect(await sysFunctionUpdatePage.getEventNameInput()).to.eq('eventName', 'Expected EventName value to be equals to eventName');
    expect(await sysFunctionUpdatePage.getHotkeyInput()).to.eq('hotkey', 'Expected Hotkey value to be equals to hotkey');
    await sysFunctionUpdatePage.save();
    expect(await sysFunctionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysFunctionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SysFunction', async () => {
    const nbButtonsBeforeDelete = await sysFunctionComponentsPage.countDeleteButtons();
    await sysFunctionComponentsPage.clickOnLastDeleteButton();

    sysFunctionDeleteDialog = new SysFunctionDeleteDialog();
    expect(await sysFunctionDeleteDialog.getDialogTitle()).to.eq('ctApp.sysFunction.delete.question');
    await sysFunctionDeleteDialog.clickOnConfirmButton();

    expect(await sysFunctionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
