import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProNSCLChiTietLoiComponentsPage,
  ProNSCLChiTietLoiDeleteDialog,
  ProNSCLChiTietLoiUpdatePage
} from './pro-nscl-chi-tiet-loi.page-object';

const expect = chai.expect;

describe('ProNSCLChiTietLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proNSCLChiTietLoiComponentsPage: ProNSCLChiTietLoiComponentsPage;
  let proNSCLChiTietLoiUpdatePage: ProNSCLChiTietLoiUpdatePage;
  let proNSCLChiTietLoiDeleteDialog: ProNSCLChiTietLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProNSCLChiTietLois', async () => {
    await navBarPage.goToEntity('pro-nscl-chi-tiet-loi');
    proNSCLChiTietLoiComponentsPage = new ProNSCLChiTietLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proNSCLChiTietLoiComponentsPage.title), 5000);
    expect(await proNSCLChiTietLoiComponentsPage.getTitle()).to.eq('ctApp.proNSCLChiTietLoi.home.title');
  });

  it('should load create ProNSCLChiTietLoi page', async () => {
    await proNSCLChiTietLoiComponentsPage.clickOnCreateButton();
    proNSCLChiTietLoiUpdatePage = new ProNSCLChiTietLoiUpdatePage();
    expect(await proNSCLChiTietLoiUpdatePage.getPageTitle()).to.eq('ctApp.proNSCLChiTietLoi.home.createOrEditLabel');
    await proNSCLChiTietLoiUpdatePage.cancel();
  });

  it('should create and save ProNSCLChiTietLois', async () => {
    const nbButtonsBeforeCreate = await proNSCLChiTietLoiComponentsPage.countDeleteButtons();

    await proNSCLChiTietLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proNSCLChiTietLoiUpdatePage.setMaLoiInput('maLoi'),
      proNSCLChiTietLoiUpdatePage.setThoiGianInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proNSCLChiTietLoiUpdatePage.gioLamSelectLastOption(),
      proNSCLChiTietLoiUpdatePage.setDxInput('5'),
      proNSCLChiTietLoiUpdatePage.setDyInput('5'),
      proNSCLChiTietLoiUpdatePage.proNSCLSelectLastOption(),
      proNSCLChiTietLoiUpdatePage.proLoiSelectLastOption()
    ]);
    expect(await proNSCLChiTietLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proNSCLChiTietLoiUpdatePage.getThoiGianInput()).to.contain(
      '2001-01-01T02:30',
      'Expected thoiGian value to be equals to 2000-12-31'
    );
    expect(await proNSCLChiTietLoiUpdatePage.getDxInput()).to.eq('5', 'Expected dx value to be equals to 5');
    expect(await proNSCLChiTietLoiUpdatePage.getDyInput()).to.eq('5', 'Expected dy value to be equals to 5');
    const selectedIsFront = proNSCLChiTietLoiUpdatePage.getIsFrontInput();
    if (await selectedIsFront.isSelected()) {
      await proNSCLChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proNSCLChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront not to be selected').to.be.false;
    } else {
      await proNSCLChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proNSCLChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront to be selected').to.be.true;
    }
    const selectedIsInside = proNSCLChiTietLoiUpdatePage.getIsInsideInput();
    if (await selectedIsInside.isSelected()) {
      await proNSCLChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proNSCLChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside not to be selected').to.be.false;
    } else {
      await proNSCLChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proNSCLChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside to be selected').to.be.true;
    }
    await proNSCLChiTietLoiUpdatePage.save();
    expect(await proNSCLChiTietLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proNSCLChiTietLoiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProNSCLChiTietLoi', async () => {
    const nbButtonsBeforeDelete = await proNSCLChiTietLoiComponentsPage.countDeleteButtons();
    await proNSCLChiTietLoiComponentsPage.clickOnLastDeleteButton();

    proNSCLChiTietLoiDeleteDialog = new ProNSCLChiTietLoiDeleteDialog();
    expect(await proNSCLChiTietLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proNSCLChiTietLoi.delete.question');
    await proNSCLChiTietLoiDeleteDialog.clickOnConfirmButton();

    expect(await proNSCLChiTietLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
