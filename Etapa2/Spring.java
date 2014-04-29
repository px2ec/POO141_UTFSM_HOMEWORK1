public class Spring extends PhysicsElement {
   private static int id=0;  // Spring identification
   protected final double restLength;
   private final double stiffness;
   protected Ball a_end, b_end;

   private Spring(){   // nobody can create a block without state
      this(0,0);
   }
   public Spring(double restLength, double stiffness){
      super(id++);
      this.restLength = restLength;
      this.stiffness = stiffness;
      a_end = b_end = null;
   }
   public void attachEnd (Ball sa) {  // note: we attach a spring to a ball, 
      if(a_end==null)                             //       not the other way around.
         a_end = sa;                     
     // to be completed by you
      sa.attachSpring(this);
   }
   private double getAendPosition() {
      if (a_end != null)
         return a_end.getPosition();
      if (b_end != null)
         return b_end.getPosition()-restLength;
      return 0;
   }
   public double getBendPosition() {
    // to be coded by you
   }
   public double getForce(Ball ball) {
      double force = 0;
      if ((a_end == null) || (b_end == null))
         return force;
      if ((ball != a_end) && (ball != b_end))
         return force;
      // to be completed by you
   }
   public void computeNextState(double delta_t, MyWorld w){
   } 
   public void updateState(){
   }

   public String getDescription() {
      return "Spring_"+ getId()+":a_end\tb_end";
   }
   public String getState() {
     //  to be coded by you
   }
}