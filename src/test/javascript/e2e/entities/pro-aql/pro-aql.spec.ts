import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProAqlComponentsPage, ProAqlDeleteDialog, ProAqlUpdatePage } from './pro-aql.page-object';

const expect = chai.expect;

describe('ProAql e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proAqlComponentsPage: ProAqlComponentsPage;
  let proAqlUpdatePage: ProAqlUpdatePage;
  let proAqlDeleteDialog: ProAqlDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProAqls', async () => {
    await navBarPage.goToEntity('pro-aql');
    proAqlComponentsPage = new ProAqlComponentsPage();
    await browser.wait(ec.visibilityOf(proAqlComponentsPage.title), 5000);
    expect(await proAqlComponentsPage.getTitle()).to.eq('ctApp.proAql.home.title');
  });

  it('should load create ProAql page', async () => {
    await proAqlComponentsPage.clickOnCreateButton();
    proAqlUpdatePage = new ProAqlUpdatePage();
    expect(await proAqlUpdatePage.getPageTitle()).to.eq('ctApp.proAql.home.createOrEditLabel');
    await proAqlUpdatePage.cancel();
  });

  it('should create and save ProAqls', async () => {
    const nbButtonsBeforeCreate = await proAqlComponentsPage.countDeleteButtons();

    await proAqlComponentsPage.clickOnCreateButton();
    await promise.all([
      proAqlUpdatePage.setAqlInput('aql'),
      proAqlUpdatePage.setSoLuongDonHangTuInput('5'),
      proAqlUpdatePage.setSoLuongDonHangDenInput('5'),
      proAqlUpdatePage.setSoLuongKiemInput('5'),
      proAqlUpdatePage.setSoLuongSeFailInput('5'),
      proAqlUpdatePage.setSoLuongSePassInput('5')
    ]);
    expect(await proAqlUpdatePage.getAqlInput()).to.eq('aql', 'Expected Aql value to be equals to aql');
    expect(await proAqlUpdatePage.getSoLuongDonHangTuInput()).to.eq('5', 'Expected soLuongDonHangTu value to be equals to 5');
    expect(await proAqlUpdatePage.getSoLuongDonHangDenInput()).to.eq('5', 'Expected soLuongDonHangDen value to be equals to 5');
    expect(await proAqlUpdatePage.getSoLuongKiemInput()).to.eq('5', 'Expected soLuongKiem value to be equals to 5');
    expect(await proAqlUpdatePage.getSoLuongSeFailInput()).to.eq('5', 'Expected soLuongSeFail value to be equals to 5');
    expect(await proAqlUpdatePage.getSoLuongSePassInput()).to.eq('5', 'Expected soLuongSePass value to be equals to 5');
    await proAqlUpdatePage.save();
    expect(await proAqlUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proAqlComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProAql', async () => {
    const nbButtonsBeforeDelete = await proAqlComponentsPage.countDeleteButtons();
    await proAqlComponentsPage.clickOnLastDeleteButton();

    proAqlDeleteDialog = new ProAqlDeleteDialog();
    expect(await proAqlDeleteDialog.getDialogTitle()).to.eq('ctApp.proAql.delete.question');
    await proAqlDeleteDialog.clickOnConfirmButton();

    expect(await proAqlComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
