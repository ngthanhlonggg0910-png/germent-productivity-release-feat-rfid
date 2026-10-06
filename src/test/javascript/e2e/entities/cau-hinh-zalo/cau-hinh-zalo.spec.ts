import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CauHinhZaloComponentsPage } from './cau-hinh-zalo.page-object';

const expect = chai.expect;

describe('CauHinhZalo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cauHinhZaloComponentsPage: CauHinhZaloComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CauHinhZalos', async () => {
    await navBarPage.goToEntity('cau-hinh-zalo');
    cauHinhZaloComponentsPage = new CauHinhZaloComponentsPage();
    await browser.wait(ec.visibilityOf(cauHinhZaloComponentsPage.title), 5000);
    expect(await cauHinhZaloComponentsPage.getTitle()).to.eq('ctApp.cauHinhZalo.home.title');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
