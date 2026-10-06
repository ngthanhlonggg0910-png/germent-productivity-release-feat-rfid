import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProFinalChiTietLoiComponentsPage,
  ProFinalChiTietLoiDeleteDialog,
  ProFinalChiTietLoiUpdatePage
} from './pro-final-chi-tiet-loi.page-object';

const expect = chai.expect;

describe('ProFinalChiTietLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proFinalChiTietLoiComponentsPage: ProFinalChiTietLoiComponentsPage;
  let proFinalChiTietLoiUpdatePage: ProFinalChiTietLoiUpdatePage;
  let proFinalChiTietLoiDeleteDialog: ProFinalChiTietLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProFinalChiTietLois', async () => {
    await navBarPage.goToEntity('pro-final-chi-tiet-loi');
    proFinalChiTietLoiComponentsPage = new ProFinalChiTietLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proFinalChiTietLoiComponentsPage.title), 5000);
    expect(await proFinalChiTietLoiComponentsPage.getTitle()).to.eq('ctApp.proFinalChiTietLoi.home.title');
  });

  it('should load create ProFinalChiTietLoi page', async () => {
    await proFinalChiTietLoiComponentsPage.clickOnCreateButton();
    proFinalChiTietLoiUpdatePage = new ProFinalChiTietLoiUpdatePage();
    expect(await proFinalChiTietLoiUpdatePage.getPageTitle()).to.eq('ctApp.proFinalChiTietLoi.home.createOrEditLabel');
    await proFinalChiTietLoiUpdatePage.cancel();
  });

  it('should create and save ProFinalChiTietLois', async () => {
    const nbButtonsBeforeCreate = await proFinalChiTietLoiComponentsPage.countDeleteButtons();

    await proFinalChiTietLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proFinalChiTietLoiUpdatePage.setMaLoiInput('maLoi'),
      proFinalChiTietLoiUpdatePage.setThoiGianInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proFinalChiTietLoiUpdatePage.setDxInput('5'),
      proFinalChiTietLoiUpdatePage.setDyInput('5'),
      proFinalChiTietLoiUpdatePage.setNguoiKiemInput('nguoiKiem'),
      proFinalChiTietLoiUpdatePage.proFinalSelectLastOption(),
      proFinalChiTietLoiUpdatePage.proLoiSelectLastOption()
    ]);
    expect(await proFinalChiTietLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proFinalChiTietLoiUpdatePage.getThoiGianInput()).to.contain(
      '2001-01-01T02:30',
      'Expected thoiGian value to be equals to 2000-12-31'
    );
    expect(await proFinalChiTietLoiUpdatePage.getDxInput()).to.eq('5', 'Expected dx value to be equals to 5');
    expect(await proFinalChiTietLoiUpdatePage.getDyInput()).to.eq('5', 'Expected dy value to be equals to 5');
    const selectedIsFront = proFinalChiTietLoiUpdatePage.getIsFrontInput();
    if (await selectedIsFront.isSelected()) {
      await proFinalChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront not to be selected').to.be.false;
    } else {
      await proFinalChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront to be selected').to.be.true;
    }
    const selectedIsInside = proFinalChiTietLoiUpdatePage.getIsInsideInput();
    if (await selectedIsInside.isSelected()) {
      await proFinalChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside not to be selected').to.be.false;
    } else {
      await proFinalChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside to be selected').to.be.true;
    }
    const selectedWarning = proFinalChiTietLoiUpdatePage.getWarningInput();
    if (await selectedWarning.isSelected()) {
      await proFinalChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning not to be selected').to.be.false;
    } else {
      await proFinalChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proFinalChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning to be selected').to.be.true;
    }
    expect(await proFinalChiTietLoiUpdatePage.getNguoiKiemInput()).to.eq('nguoiKiem', 'Expected NguoiKiem value to be equals to nguoiKiem');
    await proFinalChiTietLoiUpdatePage.save();
    expect(await proFinalChiTietLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proFinalChiTietLoiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProFinalChiTietLoi', async () => {
    const nbButtonsBeforeDelete = await proFinalChiTietLoiComponentsPage.countDeleteButtons();
    await proFinalChiTietLoiComponentsPage.clickOnLastDeleteButton();

    proFinalChiTietLoiDeleteDialog = new ProFinalChiTietLoiDeleteDialog();
    expect(await proFinalChiTietLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proFinalChiTietLoi.delete.question');
    await proFinalChiTietLoiDeleteDialog.clickOnConfirmButton();

    expect(await proFinalChiTietLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
