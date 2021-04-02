package trees_graphs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class zigzagLevelOrder {
  public List<List<Integer>> solution(TreeNode root) {
    List<List<Integer>> ans = new ArrayList();
    if(root == null) return ans;
    LinkedList<TreeNode> queue = new LinkedList();
    queue.push(root);
    boolean direction = true;
    
    while(!queue.isEmpty()) {
        int currSize = queue.size();
        List<Integer> currLevel = new ArrayList();

        for(int i=0 ; i<currSize ; i++){
            TreeNode node = queue.pop();
            
            if(direction) currLevel.add(node.val);
            else currLevel.add(0, node.val);
            
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }
        ans.add(currLevel);
        direction = !direction;
    }
    
    return ans;
}
}
