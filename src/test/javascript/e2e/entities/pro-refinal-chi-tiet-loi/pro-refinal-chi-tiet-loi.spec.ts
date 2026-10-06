import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProRefinalChiTietLoiComponentsPage,
  ProRefinalChiTietLoiDeleteDialog,
  ProRefinalChiTietLoiUpdatePage
} from './pro-refinal-chi-tiet-loi.page-object';

const expect = chai.expect;

describe('ProRefinalChiTietLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proRefinalChiTietLoiComponentsPage: ProRefinalChiTietLoiComponentsPage;
  let proRefinalChiTietLoiUpdatePage: ProRefinalChiTietLoiUpdatePage;
  let proRefinalChiTietLoiDeleteDialog: ProRefinalChiTietLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProRefinalChiTietLois', async () => {
    await navBarPage.goToEntity('pro-refinal-chi-tiet-loi');
    proRefinalChiTietLoiComponentsPage = new ProRefinalChiTietLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proRefinalChiTietLoiComponentsPage.title), 5000);
    expect(await proRefinalChiTietLoiComponentsPage.getTitle()).to.eq('ctApp.proRefinalChiTietLoi.home.title');
  });

  it('should load create ProRefinalChiTietLoi page', async () => {
    await proRefinalChiTietLoiComponentsPage.clickOnCreateButton();
    proRefinalChiTietLoiUpdatePage = new ProRefinalChiTietLoiUpdatePage();
    expect(await proRefinalChiTietLoiUpdatePage.getPageTitle()).to.eq('ctApp.proRefinalChiTietLoi.home.createOrEditLabel');
    await proRefinalChiTietLoiUpdatePage.cancel();
  });

  it('should create and save ProRefinalChiTietLois', async () => {
    const nbButtonsBeforeCreate = await proRefinalChiTietLoiComponentsPage.countDeleteButtons();

    await proRefinalChiTietLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proRefinalChiTietLoiUpdatePage.setMaLoiInput('maLoi'),
      proRefinalChiTietLoiUpdatePage.setThoiGianInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proRefinalChiTietLoiUpdatePage.setDxInput('5'),
      proRefinalChiTietLoiUpdatePage.setDyInput('5'),
      proRefinalChiTietLoiUpdatePage.proRefinalSelectLastOption(),
      proRefinalChiTietLoiUpdatePage.proLoiSelectLastOption()
    ]);
    expect(await proRefinalChiTietLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proRefinalChiTietLoiUpdatePage.getThoiGianInput()).to.contain(
      '2001-01-01T02:30',
      'Expected thoiGian value to be equals to 2000-12-31'
    );
    expect(await proRefinalChiTietLoiUpdatePage.getDxInput()).to.eq('5', 'Expected dx value to be equals to 5');
    expect(await proRefinalChiTietLoiUpdatePage.getDyInput()).to.eq('5', 'Expected dy value to be equals to 5');
    const selectedIsFront = proRefinalChiTietLoiUpdatePage.getIsFrontInput();
    if (await selectedIsFront.isSelected()) {
      await proRefinalChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront not to be selected').to.be.false;
    } else {
      await proRefinalChiTietLoiUpdatePage.getIsFrontInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getIsFrontInput().isSelected(), 'Expected isFront to be selected').to.be.true;
    }
    const selectedIsInside = proRefinalChiTietLoiUpdatePage.getIsInsideInput();
    if (await selectedIsInside.isSelected()) {
      await proRefinalChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside not to be selected').to.be.false;
    } else {
      await proRefinalChiTietLoiUpdatePage.getIsInsideInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getIsInsideInput().isSelected(), 'Expected isInside to be selected').to.be.true;
    }
    const selectedWarning = proRefinalChiTietLoiUpdatePage.getWarningInput();
    if (await selectedWarning.isSelected()) {
      await proRefinalChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning not to be selected').to.be.false;
    } else {
      await proRefinalChiTietLoiUpdatePage.getWarningInput().click();
      expect(await proRefinalChiTietLoiUpdatePage.getWarningInput().isSelected(), 'Expected warning to be selected').to.be.true;
    }
    await proRefinalChiTietLoiUpdatePage.save();
    expect(await proRefinalChiTietLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proRefinalChiTietLoiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProRefinalChiTietLoi', async () => {
    const nbButtonsBeforeDelete = await proRefinalChiTietLoiComponentsPage.countDeleteButtons();
    await proRefinalChiTietLoiComponentsPage.clickOnLastDeleteButton();

    proRefinalChiTietLoiDeleteDialog = new ProRefinalChiTietLoiDeleteDialog();
    expect(await proRefinalChiTietLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proRefinalChiTietLoi.delete.question');
    await proRefinalChiTietLoiDeleteDialog.clickOnConfirmButton();

    expect(await proRefinalChiTietLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
