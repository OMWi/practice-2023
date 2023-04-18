import { ListItem, Paper, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

export default function WordItem({ wordData }) {
  const navigate = useNavigate();

  return (
    <ListItem
      disablePadding
      sx={{
        mb: 1,
        "&:hover": {
          cursor: "pointer",
        },
      }}
      onClick={() => navigate(`/words/${wordData.id}`)}
    >
      <Paper square sx={{ width: 1 }}>
        <Stack sx={{ padding: 1, width: 1 }}>
          <Stack direction="row" alignItems="flex-end">
            <Typography variant="h6" sx={{ display: "flex", flexGrow: 1 }}>
              {wordData.text}
            </Typography>
            <Typography
              variant="subtitle1"
              display="inline"
              fontStyle="italic"
              sx={{ ml: 1 }}
            >
              {wordData.category}
            </Typography>
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}

WordItem.propTypes = {
  wordData: PropTypes.shape({
    id: PropTypes.number,
    text: PropTypes.string,
    category: PropTypes.string,
  }),
};
