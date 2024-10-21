export class PlansSubmited {
    planName: string;
    price: number;
    urlSuccess: string;
    urlCancel: string;

    constructor(
        planName: string,
        price: number,
        urlSuccess: string,
        urlCancel: string){
        this.planName= planName;
        this.price = price;
        this.urlSuccess = urlSuccess;
        this.urlCancel = urlCancel;
    }

  }