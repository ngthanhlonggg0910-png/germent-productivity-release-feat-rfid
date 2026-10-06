import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProLoiComponentsPage, ProLoiDeleteDialog, ProLoiUpdatePage } from './pro-loi.page-object';

const expect = chai.expect;

describe('ProLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proLoiComponentsPage: ProLoiComponentsPage;
  let proLoiUpdatePage: ProLoiUpdatePage;
  let proLoiDeleteDialog: ProLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProLois', async () => {
    await navBarPage.goToEntity('pro-loi');
    proLoiComponentsPage = new ProLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proLoiComponentsPage.title), 5000);
    expect(await proLoiComponentsPage.getTitle()).to.eq('ctApp.proLoi.home.title');
  });

  it('should load create ProLoi page', async () => {
    await proLoiComponentsPage.clickOnCreateButton();
    proLoiUpdatePage = new ProLoiUpdatePage();
    expect(await proLoiUpdatePage.getPageTitle()).to.eq('ctApp.proLoi.home.createOrEditLabel');
    await proLoiUpdatePage.cancel();
  });

  it('should create and save ProLois', async () => {
    const nbButtonsBeforeCreate = await proLoiComponentsPage.countDeleteButtons();

    await proLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proLoiUpdatePage.setMaLoiInput('maLoi'),
      proLoiUpdatePage.setCusCodeInput('cusCode'),
      proLoiUpdatePage.setTenLoiInput('tenLoi'),
      proLoiUpdatePage.setTenTiengAnhInput('tenTiengAnh'),
      proLoiUpdatePage.setLabelInput('label'),
      proLoiUpdatePage.setSttInput('5'),
      proLoiUpdatePage.setMauSacInput('mauSac'),
      proLoiUpdatePage.setGhiChuInput('ghiChu'),
      proLoiUpdatePage.proCumLoiSelectLastOption()
    ]);
    expect(await proLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proLoiUpdatePage.getCusCodeInput()).to.eq('cusCode', 'Expected CusCode value to be equals to cusCode');
    expect(await proLoiUpdatePage.getTenLoiInput()).to.eq('tenLoi', 'Expected TenLoi value to be equals to tenLoi');
    expect(await proLoiUpdatePage.getTenTiengAnhInput()).to.eq('tenTiengAnh', 'Expected TenTiengAnh value to be equals to tenTiengAnh');
    expect(await proLoiUpdatePage.getLabelInput()).to.eq('label', 'Expected Label value to be equals to label');
    expect(await proLoiUpdatePage.getSttInput()).to.eq('5', 'Expected stt value to be equals to 5');
    expect(await proLoiUpdatePage.getMauSacInput()).to.eq('mauSac', 'Expected MauSac value to be equals to mauSac');
    const selectedQc = proLoiUpdatePage.getQcInput();
    if (await selectedQc.isSelected()) {
      await proLoiUpdatePage.getQcInput().click();
      expect(await proLoiUpdatePage.getQcInput().isSelected(), 'Expected qc not to be selected').to.be.false;
    } else {
      await proLoiUpdatePage.getQcInput().click();
      expect(await proLoiUpdatePage.getQcInput().isSelected(), 'Expected qc to be selected').to.be.true;
    }
    const selectedQa = proLoiUpdatePage.getQaInput();
    if (await selectedQa.isSelected()) {
      await proLoiUpdatePage.getQaInput().click();
      expect(await proLoiUpdatePage.getQaInput().isSelected(), 'Expected qa not to be selected').to.be.false;
    } else {
      await proLoiUpdatePage.getQaInput().click();
      expect(await proLoiUpdatePage.getQaInput().isSelected(), 'Expected qa to be selected').to.be.true;
    }
    const selectedChecked = proLoiUpdatePage.getCheckedInput();
    if (await selectedChecked.isSelected()) {
      await proLoiUpdatePage.getCheckedInput().click();
      expect(await proLoiUpdatePage.getCheckedInput().isSelected(), 'Expected checked not to be selected').to.be.false;
    } else {
      await proLoiUpdatePage.getCheckedInput().click();
      expect(await proLoiUpdatePage.getCheckedInput().isSelected(), 'Expected checked to be selected').to.be.true;
    }
    expect(await proLoiUpdatePage.getGhiChuInput()).to.eq('ghiChu', 'Expected GhiChu value to be equals to ghiChu');
    await proLoiUpdatePage.save();
    expect(await proLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProLoi', async () => {
    const nbButtonsBeforeDelete = await proLoiComponentsPage.countDeleteButtons();
    await proLoiComponentsPage.clickOnLastDeleteButton();

    proLoiDeleteDialog = new ProLoiDeleteDialog();
    expect(await proLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proLoi.delete.question');
    await proLoiDeleteDialog.clickOnConfirmButton();

    expect(await proLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
