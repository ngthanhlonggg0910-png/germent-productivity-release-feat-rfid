import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProRefinalCCChiTietLoiComponentsPage,
  ProRefinalCCChiTietLoiDeleteDialog,
  ProRefinalCCChiTietLoiUpdatePage
} from './pro-refinal-cc-chi-tiet-loi.page-object';

const expect = chai.expect;

describe('ProRefinalCCChiTietLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proRefinalCCChiTietLoiComponentsPage: ProRefinalCCChiTietLoiComponentsPage;
  let proRefinalCCChiTietLoiUpdatePage: ProRefinalCCChiTietLoiUpdatePage;
  let proRefinalCCChiTietLoiDeleteDialog: ProRefinalCCChiTietLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProRefinalCCChiTietLois', async () => {
    await navBarPage.goToEntity('pro-refinal-cc-chi-tiet-loi');
    proRefinalCCChiTietLoiComponentsPage = new ProRefinalCCChiTietLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proRefinalCCChiTietLoiComponentsPage.title), 5000);
    expect(await proRefinalCCChiTietLoiComponentsPage.getTitle()).to.eq('ctApp.proRefinalCCChiTietLoi.home.title');
  });

  it('should load create ProRefinalCCChiTietLoi page', async () => {
    await proRefinalCCChiTietLoiComponentsPage.clickOnCreateButton();
    proRefinalCCChiTietLoiUpdatePage = new ProRefinalCCChiTietLoiUpdatePage();
    expect(await proRefinalCCChiTietLoiUpdatePage.getPageTitle()).to.eq('ctApp.proRefinalCCChiTietLoi.home.createOrEditLabel');
    await proRefinalCCChiTietLoiUpdatePage.cancel();
  });

  it('should create and save ProRefinalCCChiTietLois', async () => {
    const nbButtonsBeforeCreate = await proRefinalCCChiTietLoiComponentsPage.countDeleteButtons();

    await proRefinalCCChiTietLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proRefinalCCChiTietLoiUpdatePage.setMaLoiInput('maLoi'),
      proRefinalCCChiTietLoiUpdatePage.setThoiGianInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proRefinalCCChiTietLoiUpdatePage.setDxInput('5'),
      proRefinalCCChiTietLoiUpdatePage.setDyInput('5'),
      proRefinalCCChiTietLoiUpdatePage.setNguoiKiemInput('nguoiKiem'),
      proRefinalCCChiTietLoiUpdatePage.proRefinalCCSelectLastOption(),
      proRefinalCCChiTietLoiUpdatePage.proLoiSelectLastOption()
    ]);
    expect(await proRefinalCCChiTietLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proRefinalCCChiTietLoiUpdatePage.getThoiGianInput()).to.contain(
      '2001-01-01T02:30',
      'Expected thoiGian value to be equals to 2000-12-31'
    );
    expect(await proRefinalCCChiTietLoiUpdatePage.getDxInput()).to.eq('5', 'Expected dx value to be equals to 5');
    expect(await proRefinalCCChiTietLoiUpdatePage.getDyInput()).to.eq('5', 'Expected dy value to be equals to 5');
    const selectedIsFront = proRefinalCCChiTietLoiUpdatePage.getIsFrontInput();
    if (await selectedIsFront.isSelected()) {
      await proRefinalCCChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront not to be selected').to.be.false;
    } else {
      await proRefinalCCChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront to be selected').to.be.true;
    }
    const selectedIsInside = proRefinalCCChiTietLoiUpdatePage.getIsInsideInput();
    if (await selectedIsInside.isSelected()) {
      await proRefinalCCChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside not to be selected').to.be.false;
    } else {
      await proRefinalCCChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside to be selected').to.be.true;
    }
    const selectedWarning = proRefinalCCChiTietLoiUpdatePage.getWarningInput();
    if (await selectedWarning.isSelected()) {
      await proRefinalCCChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning not to be selected').to.be.false;
    } else {
      await proRefinalCCChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proRefinalCCChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning to be selected').to.be.true;
    }
    expect(await proRefinalCCChiTietLoiUpdatePage.getNguoiKiemInput()).to.eq(
      'nguoiKiem',
      'Expected NguoiKiem value to be equals to nguoiKiem'
    );
    await proRefinalCCChiTietLoiUpdatePage.save();
    expect(await proRefinalCCChiTietLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proRefinalCCChiTietLoiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProRefinalCCChiTietLoi', async () => {
    const nbButtonsBeforeDelete = await proRefinalCCChiTietLoiComponentsPage.countDeleteButtons();
    await proRefinalCCChiTietLoiComponentsPage.clickOnLastDeleteButton();

    proRefinalCCChiTietLoiDeleteDialog = new ProRefinalCCChiTietLoiDeleteDialog();
    expect(await proRefinalCCChiTietLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proRefinalCCChiTietLoi.delete.question');
    await proRefinalCCChiTietLoiDeleteDialog.clickOnConfirmButton();

    expect(await proRefinalCCChiTietLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
