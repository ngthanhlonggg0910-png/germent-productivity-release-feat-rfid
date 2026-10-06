import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProTaiKhoanCheckPointComponentsPage,
  ProTaiKhoanCheckPointDeleteDialog,
  ProTaiKhoanCheckPointUpdatePage
} from './pro-tai-khoan-check-point.page-object';

const expect = chai.expect;

describe('ProTaiKhoanCheckPoint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proTaiKhoanCheckPointComponentsPage: ProTaiKhoanCheckPointComponentsPage;
  let proTaiKhoanCheckPointUpdatePage: ProTaiKhoanCheckPointUpdatePage;
  let proTaiKhoanCheckPointDeleteDialog: ProTaiKhoanCheckPointDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProTaiKhoanCheckPoints', async () => {
    await navBarPage.goToEntity('pro-tai-khoan-check-point');
    proTaiKhoanCheckPointComponentsPage = new ProTaiKhoanCheckPointComponentsPage();
    await browser.wait(ec.visibilityOf(proTaiKhoanCheckPointComponentsPage.title), 5000);
    expect(await proTaiKhoanCheckPointComponentsPage.getTitle()).to.eq('ctApp.proTaiKhoanCheckPoint.home.title');
  });

  it('should load create ProTaiKhoanCheckPoint page', async () => {
    await proTaiKhoanCheckPointComponentsPage.clickOnCreateButton();
    proTaiKhoanCheckPointUpdatePage = new ProTaiKhoanCheckPointUpdatePage();
    expect(await proTaiKhoanCheckPointUpdatePage.getPageTitle()).to.eq('ctApp.proTaiKhoanCheckPoint.home.createOrEditLabel');
    await proTaiKhoanCheckPointUpdatePage.cancel();
  });

  it('should create and save ProTaiKhoanCheckPoints', async () => {
    const nbButtonsBeforeCreate = await proTaiKhoanCheckPointComponentsPage.countDeleteButtons();

    await proTaiKhoanCheckPointComponentsPage.clickOnCreateButton();
    await promise.all([
      proTaiKhoanCheckPointUpdatePage.setTaiKhoanInput('taiKhoan'),
      proTaiKhoanCheckPointUpdatePage.proCheckPointSelectLastOption()
    ]);
    expect(await proTaiKhoanCheckPointUpdatePage.getTaiKhoanInput()).to.eq('taiKhoan', 'Expected TaiKhoan value to be equals to taiKhoan');
    const selectedChecked = proTaiKhoanCheckPointUpdatePage.getCheckedInput();
    if (await selectedChecked.isSelected()) {
      await proTaiKhoanCheckPointUpdatePage.getCheckedInput().click();
      expect(await proTaiKhoanCheckPointUpdatePage.getCheckedInput().isSelected(), 'Expected checked not to be selected').to.be.false;
    } else {
      await proTaiKhoanCheckPointUpdatePage.getCheckedInput().click();
      expect(await proTaiKhoanCheckPointUpdatePage.getCheckedInput().isSelected(), 'Expected checked to be selected').to.be.true;
    }
    await proTaiKhoanCheckPointUpdatePage.save();
    expect(await proTaiKhoanCheckPointUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proTaiKhoanCheckPointComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProTaiKhoanCheckPoint', async () => {
    const nbButtonsBeforeDelete = await proTaiKhoanCheckPointComponentsPage.countDeleteButtons();
    await proTaiKhoanCheckPointComponentsPage.clickOnLastDeleteButton();

    proTaiKhoanCheckPointDeleteDialog = new ProTaiKhoanCheckPointDeleteDialog();
    expect(await proTaiKhoanCheckPointDeleteDialog.getDialogTitle()).to.eq('ctApp.proTaiKhoanCheckPoint.delete.question');
    await proTaiKhoanCheckPointDeleteDialog.clickOnConfirmButton();

    expect(await proTaiKhoanCheckPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
