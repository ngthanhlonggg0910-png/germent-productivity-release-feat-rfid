import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProNhaMayComponentsPage, ProNhaMayDeleteDialog, ProNhaMayUpdatePage } from './pro-nha-may.page-object';

const expect = chai.expect;

describe('ProNhaMay e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proNhaMayComponentsPage: ProNhaMayComponentsPage;
  let proNhaMayUpdatePage: ProNhaMayUpdatePage;
  let proNhaMayDeleteDialog: ProNhaMayDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProNhaMays', async () => {
    await navBarPage.goToEntity('pro-nha-may');
    proNhaMayComponentsPage = new ProNhaMayComponentsPage();
    await browser.wait(ec.visibilityOf(proNhaMayComponentsPage.title), 5000);
    expect(await proNhaMayComponentsPage.getTitle()).to.eq('ctApp.proNhaMay.home.title');
  });

  it('should load create ProNhaMay page', async () => {
    await proNhaMayComponentsPage.clickOnCreateButton();
    proNhaMayUpdatePage = new ProNhaMayUpdatePage();
    expect(await proNhaMayUpdatePage.getPageTitle()).to.eq('ctApp.proNhaMay.home.createOrEditLabel');
    await proNhaMayUpdatePage.cancel();
  });

  it('should create and save ProNhaMays', async () => {
    const nbButtonsBeforeCreate = await proNhaMayComponentsPage.countDeleteButtons();

    await proNhaMayComponentsPage.clickOnCreateButton();
    await promise.all([
      proNhaMayUpdatePage.setTenInput('ten'),
      proNhaMayUpdatePage.setSttInput('5'),
      proNhaMayUpdatePage.proCongTySelectLastOption()
    ]);
    expect(await proNhaMayUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proNhaMayUpdatePage.getSttInput()).to.eq('5', 'Expected stt value to be equals to 5');
    await proNhaMayUpdatePage.save();
    expect(await proNhaMayUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proNhaMayComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProNhaMay', async () => {
    const nbButtonsBeforeDelete = await proNhaMayComponentsPage.countDeleteButtons();
    await proNhaMayComponentsPage.clickOnLastDeleteButton();

    proNhaMayDeleteDialog = new ProNhaMayDeleteDialog();
    expect(await proNhaMayDeleteDialog.getDialogTitle()).to.eq('ctApp.proNhaMay.delete.question');
    await proNhaMayDeleteDialog.clickOnConfirmButton();

    expect(await proNhaMayComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
