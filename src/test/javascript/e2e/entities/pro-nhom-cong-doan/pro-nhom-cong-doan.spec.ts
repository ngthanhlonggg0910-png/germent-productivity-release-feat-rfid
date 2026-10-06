import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProNhomCongDoanComponentsPage, ProNhomCongDoanDeleteDialog, ProNhomCongDoanUpdatePage } from './pro-nhom-cong-doan.page-object';

const expect = chai.expect;

describe('ProNhomCongDoan e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proNhomCongDoanComponentsPage: ProNhomCongDoanComponentsPage;
  let proNhomCongDoanUpdatePage: ProNhomCongDoanUpdatePage;
  let proNhomCongDoanDeleteDialog: ProNhomCongDoanDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProNhomCongDoans', async () => {
    await navBarPage.goToEntity('pro-nhom-cong-doan');
    proNhomCongDoanComponentsPage = new ProNhomCongDoanComponentsPage();
    await browser.wait(ec.visibilityOf(proNhomCongDoanComponentsPage.title), 5000);
    expect(await proNhomCongDoanComponentsPage.getTitle()).to.eq('ctApp.proNhomCongDoan.home.title');
  });

  it('should load create ProNhomCongDoan page', async () => {
    await proNhomCongDoanComponentsPage.clickOnCreateButton();
    proNhomCongDoanUpdatePage = new ProNhomCongDoanUpdatePage();
    expect(await proNhomCongDoanUpdatePage.getPageTitle()).to.eq('ctApp.proNhomCongDoan.home.createOrEditLabel');
    await proNhomCongDoanUpdatePage.cancel();
  });

  it('should create and save ProNhomCongDoans', async () => {
    const nbButtonsBeforeCreate = await proNhomCongDoanComponentsPage.countDeleteButtons();

    await proNhomCongDoanComponentsPage.clickOnCreateButton();
    await promise.all([proNhomCongDoanUpdatePage.setTenInput('ten')]);
    expect(await proNhomCongDoanUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    const selectedCheckNangSuat = proNhomCongDoanUpdatePage.getCheckNangSuatInput();
    if (await selectedCheckNangSuat.isSelected()) {
      await proNhomCongDoanUpdatePage.getCheckNangSuatInput().click();
      expect(await proNhomCongDoanUpdatePage.getCheckNangSuatInput().isSelected(), 'Expected checkNangSuat not to be selected').to.be.false;
    } else {
      await proNhomCongDoanUpdatePage.getCheckNangSuatInput().click();
      expect(await proNhomCongDoanUpdatePage.getCheckNangSuatInput().isSelected(), 'Expected checkNangSuat to be selected').to.be.true;
    }
    await proNhomCongDoanUpdatePage.save();
    expect(await proNhomCongDoanUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proNhomCongDoanComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProNhomCongDoan', async () => {
    const nbButtonsBeforeDelete = await proNhomCongDoanComponentsPage.countDeleteButtons();
    await proNhomCongDoanComponentsPage.clickOnLastDeleteButton();

    proNhomCongDoanDeleteDialog = new ProNhomCongDoanDeleteDialog();
    expect(await proNhomCongDoanDeleteDialog.getDialogTitle()).to.eq('ctApp.proNhomCongDoan.delete.question');
    await proNhomCongDoanDeleteDialog.clickOnConfirmButton();

    expect(await proNhomCongDoanComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
