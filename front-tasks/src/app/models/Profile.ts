export class Profile{

userId?:   String;  
name?: String;
avatar?: String;

    constructor(userId: string, name: string, avatar: string){
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }


}