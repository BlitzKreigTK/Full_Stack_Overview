const person = {
    // Variable
    name: 'Tulsi',
    age: 25,
    // Person(new address())
    address: {
        line1: 'Sector 137',
        line2: 'Noida',
        country: 'India'
    },
    // Array
    socialNetwork:['twitter','facebook','linkedin','instagram'],
    // Method
    printsocialNetwork: () => {
        person.socialNetwork.map(
            (social) => {
                // Console Print
                console.log(social);
            }
        )
    }
}



export default function LearnJS(){
    return(
        <>
            <div>Learning Java Script</div>
            <div>{person.name}</div>
            <div>{person.address.line1}</div>
            <div>{person.socialNetwork[3]}</div>
            <div>{person.socialNetwork}</div>
            <div>{person.printsocialNetwork()}</div>
        </>
    )
}